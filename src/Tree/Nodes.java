package Tree;

/** Класс для создания объектов с наименованиями узлов дерева и их типами.
 *
 * @author  Nikita Mabatov
 * @version 1.6
 * @since   10.05.2018 */
public class Nodes {
    String type;
    String name;

    /**
     * Конструктор - создание нового объекта
     * @param type - тип узла
     * @param name - имя узла
     * @see Nodes#Nodes(String, String)
     */
    public Nodes(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
