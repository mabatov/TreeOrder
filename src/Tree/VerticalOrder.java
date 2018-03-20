package Tree;

import POJO.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class VerticalOrder {


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

    {
        try {
            String urlR = XMLParse.setR();
            URL objR = new URL(urlR);
            HttpURLConnection conR = (HttpURLConnection) objR.openConnection();


            br_relations = new BufferedReader(new InputStreamReader(conR.getInputStream()));
            relations = gson.fromJson(br_relations, Relations[].class);

                String urlRD = XMLParse.setRD();
                String urlGR = XMLParse.setGr();
                String urlPR = XMLParse.setPr();
                String urlUS = XMLParse.setUs();

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

                br_relatDescr = new BufferedReader(new InputStreamReader(conRD.getInputStream()));
                br_groups = new BufferedReader(new InputStreamReader(conGR.getInputStream()));
                br_projects = new BufferedReader(new InputStreamReader(conPR.getInputStream()));
                br_users = new BufferedReader(new InputStreamReader(conUS.getInputStream()));
                relDescr = gson.fromJson(br_relatDescr, RelationDescription[].class);
                groups = gson.fromJson(br_groups, Groups[].class);
                projects = gson.fromJson(br_projects, Projects[].class);
                users = gson.fromJson(br_users, Users[].class);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static class TreeNode
    {
        long id;
        public ArrayList<TreeNode> childs = new ArrayList<>();

        TreeNode(long id)
        {
            this.id=id;
        }

        TreeNode(ArrayList<TreeNode> childs) {
            this.childs = childs;
        }
    }


    public void postOrder(TreeNode root) {

        try {

            if(root !=  null) {
                //System.out.printf("%d \n", root.id);

                for (Relations r : relations) {
                    if (r.getParent().equals(root.id) && r.getLevel().equals(firstLevel)) {
                        postOrder(new TreeNode(r.getNode()));
                    }
                }

                for (RelationDescription rd : relDescr) {
                    if (rd.getId().equals(root.id)) {
                        //для групп
                        if (rd.getType().equals("Group")) {
                            for (Groups gr : groups) {
                                if (rd.getObjectId().equals(gr.getId())) {
                                    System.out.println("Group: " + gr.getName());
                                }
                            }
                        }
                        //для проектов
                        if (rd.getType().equals("Project")) {
                            for (Projects pr : projects) {
                                if (rd.getObjectId().equals(pr.getId())) {
                                    System.out.println("Project: " + pr.getName());
                                }
                            }
                        }
                        //для юзеров
                        if (rd.getType().equals("User")) {
                            for (Users us : users) {
                                if (rd.getObjectId().equals(us.getId())) {
                                    System.out.println("User: " + us.getLogin());
                                    System.out.println(us.getEmail());
                                }
                            }
                        }
                        //print Emails
                        /*for (Relations r : relations) {
                            if (rd.getId().equals(r.getParent())) {
                                for (RelationDescription rd2 : relDescr) {
                                    if (rd2.getId().equals(r.getNode()) && rd2.getType().equals("User")) {
                                        for (Users us : users) {
                                            System.out.println("!Email = " + us.getEmail());
                                        }
                                    }
                                }
                            }
                        }*/
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (br_relatDescr != null) { try { br_relatDescr.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_groups != null) { try { br_groups.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_projects != null) { try { br_projects.close(); } catch (IOException e) { e.printStackTrace(); } }
            if (br_users != null) { try { br_users.close(); } catch (IOException e) { e.printStackTrace(); } }

            if (br_relations != null) {
                try {
                    br_relations.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args)
    {
        VerticalOrder bi = new VerticalOrder();

        TreeNode rootNode = new TreeNode(7103);
        System.out.println("Using Recursive solution:");

        bi.postOrder(rootNode);

    }
}