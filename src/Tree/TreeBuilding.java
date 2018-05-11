package Tree;

import POJO.*;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


/**
 * Класс перебора элементов дерева с использованием алгоритма
 * "Обход дерева в глубину" и рекурсивной функции.
 *
 * @author  Nikita Mabatov
 * @version 1.6
 * @since   10.05.2018
 */

public class TreeBuilding
{
    /** Поле идентификатора узла дерева */
    public long id;

    /** Массив с идентификаторами дочерних узлов для конкретного элемента */
    public ArrayList<TreeBuilding> children = new ArrayList<>();
    /** Поле почтового адреса узла */
    public StringBuilder email = new StringBuilder();

    /**
     * Конструктор - создание нового объекта
     * @param id - идентификатор узла
     * @see TreeBuilding#TreeBuilding(long)
     */
    public TreeBuilding(long id)
    {
        this.id=id;
    }

    /** Поле класса Gson, предназначенного для работы с JSON-форматом */
    public static Gson gson = new Gson();
    /** Поле первый уровень */
    public static long firstLevel = 1;

    /** Поле сущность класса BufferedReader */
    public static BufferedReader br_entity = null;

    /** Группа массивов обращения к классам
     * структуры, описывающей конкретный JSON-файл */
    public static Relations[] relations;
    public static RelationDescription[] relDescr;
    public static Groups[] groups;
    public static Projects[] projects;
    public static Users[] users;

    /** Хэш-таблица, содержащая данные об узлах.
     * В качестве ключа - объекты класса Nodes {@link Nodes} */
    public static Map<Nodes, StringBuilder> nodeHashMap = new LinkedHashMap<>();

    /** Функция последовательной передачи параметров (сущностей) в класс {@link XMLParsing}
     * @exception Exception Отлавливает любой тип исключения
     * @exception IOException Отлавливает исключения ввода-вывода */
    public static void parseXML()
    {
        try {

            String[] entities = {"relations", "relatDescr", "groups", "projects", "users"};

            for (String entity : entities)
            {
                URL url = new URL(XMLParsing.setNodeName(entity));
                HttpURLConnection conURL = (HttpURLConnection) url.openConnection();
                br_entity = new BufferedReader(new InputStreamReader(conURL.getInputStream()));

                switch (entity) {
                    case "relations" : {
                        relations = gson.fromJson(br_entity, Relations[].class);
                    } break;
                    case "relatDescr" : {
                        relDescr = gson.fromJson(br_entity, RelationDescription[].class);
                    } break;
                    case "groups" : {
                        groups = gson.fromJson(br_entity, Groups[].class);
                    } break;
                    case "projects" : {
                        projects = gson.fromJson(br_entity, Projects[].class);
                    } break;
                    case "users" : {
                        users = gson.fromJson(br_entity, Users[].class);
                    } break;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (br_entity != null) { try { br_entity.close(); } catch (IOException e) { e.printStackTrace(); } }
        }
    }


    /** Рекурсивная функция обхода всех узлов дерева
     * @return возвращает ссылку на текущий объект (идентификатор узла) */
    public TreeBuilding postOrder() {

        for (Relations r : relations) {
            if (r.getParent().equals(this.id) && r.getLevel().equals(firstLevel)) {
                TreeBuilding n = new TreeBuilding(r.getNode());
                n.postOrder();
                this.children.add(n);
            }
        }

        for (RelationDescription rd : relDescr) {
            if (rd.getId().equals(this.id)) {
                switch (rd.getType()) {

                    case "Group": {
                        for (Groups gr : groups) {
                            if (rd.getObjectId().equals(gr.getId())) {
                                nodeHashMap.put(new Nodes("Group", gr.getName()), printEmail(this));
                            }
                        }
                    } break;

                    case "Project": {
                        for (Projects pr : projects) {
                            if (rd.getObjectId().equals(pr.getId())) {
                                nodeHashMap.put(new Nodes("Project", pr.getName()), printEmail(this));
                            }
                        }
                    } break;

                    case "User": {
                        for (Users us : users) {
                            if (rd.getObjectId().equals(us.getId())) {
                                nodeHashMap.put(new Nodes("User", us.getLogin()), printEmail(this));
                            }
                        }
                    } break;
                }
            }
        }
        return this;
    }

    /** Рекурсивная функция присвоения узлу всех возможных почтовых адресов
     * его дочерних элементов, если такие имеются
     * @param root - идентификатор узла
     * @return возвращает ссылку на текущий объект (идентификатор узла) */
    public StringBuilder printEmail(TreeBuilding root){

        if (root.email == null || root.email.toString().equals("")) {
            for (RelationDescription rd : relDescr) {
                if (rd.getId().equals(root.id) && rd.getType().equals("User")) {
                    for (Users us : users) {
                        if (rd.getObjectId().equals(us.getId())) {
                            root.email.append(us.getEmail() + ";");
                            return root.email;
                        }
                    }
                }
            }
        }

        if (root.email.lastIndexOf(";") == -1) {
            for (TreeBuilding childNode : root.children) {
                root.email.append(printEmail(childNode));
            }
        }

        return root.email;
    }

    /** Функция вывода всех значений хэш-таблицы
     * {@link TreeBuilding#nodeHashMap} в консоль */
    public static void consoleOutput(){
        for (Map.Entry<Nodes, StringBuilder> entry : nodeHashMap.entrySet()) {
            String typeWithName = entry.getKey().type + ": " + entry.getKey().name;
            StringBuilder email = entry.getValue();
            System.out.println(typeWithName + "\n" + email);
        }
    }

    /** Основная функция, в которой запускается в нужном порядке
     * вся фунцкиональная логика класса {@link TreeBuilding} */
    public static void main(String[] args)
    {
        parseXML();

        /** Объект класса {@link TreeBuilding}, содержащий в качестве
         * параметра идентификатор корневого элемента дерева */
        TreeBuilding rootNode = new TreeBuilding(7103);
        System.out.println("Using Recursive solution:");

        rootNode.postOrder();

        consoleOutput();
    }
}