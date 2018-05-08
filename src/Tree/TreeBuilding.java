package Tree;

import POJO.*;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TreeBuilding
{
    public long id;

    public ArrayList<TreeBuilding> children = new ArrayList<>();
    public StringBuilder email = new StringBuilder();

    public TreeBuilding(long id)
    {
        this.id=id;
    }

    public static Gson gson = new Gson();
    public static long firstLevel = 1;

    public static Relations[] relations;
    public static RelationDescription[] relDescr;
    public static Groups[] groups;
    public static Projects[] projects;
    public static Users[] users;

    public static BufferedReader br_relations = null;
    public static BufferedReader br_relatDescr = null;
    public static BufferedReader br_groups = null;
    public static BufferedReader br_projects = null;
    public static BufferedReader br_users = null;

    public static Map<String, StringBuilder> nodeHashMap = new LinkedHashMap<>();


    public static void parseXML()
    {
        try {
                String urlR = XMLParse.setNodeName("relations");
                String urlRD = XMLParse.setNodeName("relatDescr");
                String urlGR = XMLParse.setNodeName("groups");
                String urlPR = XMLParse.setNodeName("projects");
                String urlUS = XMLParse.setNodeName("users");

                //for relations
                URL objR = new URL(urlR);
                HttpURLConnection conR = (HttpURLConnection) objR.openConnection();
                //for relatDescr
                URL objRD = new URL(urlRD);
                HttpURLConnection conRD = (HttpURLConnection) objRD.openConnection();
                //for groups
                URL objGR = new URL(urlGR);
                HttpURLConnection conGR = (HttpURLConnection) objGR.openConnection();
                //for projects
                URL objPR = new URL(urlPR);
                HttpURLConnection conPR = (HttpURLConnection) objPR.openConnection();
                //for users
                URL objUS = new URL(urlUS);
                HttpURLConnection conUS = (HttpURLConnection) objUS.openConnection();

                br_relations = new BufferedReader(new InputStreamReader(conR.getInputStream()));
                br_relatDescr = new BufferedReader(new InputStreamReader(conRD.getInputStream()));
                br_groups = new BufferedReader(new InputStreamReader(conGR.getInputStream()));
                br_projects = new BufferedReader(new InputStreamReader(conPR.getInputStream()));
                br_users = new BufferedReader(new InputStreamReader(conUS.getInputStream()));
                relations = gson.fromJson(br_relations, Relations[].class);
                relDescr = gson.fromJson(br_relatDescr, RelationDescription[].class);
                groups = gson.fromJson(br_groups, Groups[].class);
                projects = gson.fromJson(br_projects, Projects[].class);
                users = gson.fromJson(br_users, Users[].class);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (br_relatDescr != null) { try { br_relatDescr.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_groups != null) { try { br_groups.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_projects != null) { try { br_projects.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_users != null) { try { br_users.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_relations != null) { try { br_relations.close(); } catch (IOException e) { e.printStackTrace(); } }
        }
    }



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
                                nodeHashMap.put("Group: " + gr.getName(), printEmail(this));
                            }
                        }
                    } break;

                    case "Project": {
                        for (Projects pr : projects) {
                            if (rd.getObjectId().equals(pr.getId())) {
                                nodeHashMap.put("Project: " + pr.getName(), printEmail(this));
                            }
                        }
                    } break;

                    case "User": {
                        for (Users us : users) {
                            if (rd.getObjectId().equals(us.getId())) {
                                nodeHashMap.put("User: " + us.getLogin(), printEmail(this));
                            }
                        }
                    } break;
                }
            }
        }

        return this;
    }


    public StringBuilder printEmail(TreeBuilding root){

        if (root.email == null || root.email.toString().equals("")) {
            for (RelationDescription rd : relDescr) {
                if (rd.getId().equals(root.id) && rd.getType().equals("User")) {
                    for (Users us : users) {
                        if (rd.getObjectId().equals(us.getId())) {
                            root.email.append(us.getEmail() + ";");
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


    public static void consoleOutput(){
        for (Map.Entry<String, StringBuilder> entry : nodeHashMap.entrySet()) {
            String typeWithName = entry.getKey();
            StringBuilder email = entry.getValue();
            System.out.println(typeWithName + "\n" + email);
        }
    }

    public static void main(String[] args)
    {
        parseXML();
        TreeBuilding rootNode = new TreeBuilding(7103);
        System.out.println("Using Recursive solution:");

        rootNode.postOrder();

        consoleOutput();

    }
}