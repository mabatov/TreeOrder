package Tree;

import POJO.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class VerticalOrder {

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

    public static TreeNode createBinaryTree()
    {
        TreeNode rootNode=new TreeNode(7103);
        rootNode.childs.add(rootNode);

        /*В куске ниже приводится
         * создание,
         * инициализация и
         * установление взаимоотношений
         * между узлами дерева
         * UPD: логика отношений удалена*/
        Gson gson = new Gson();
        BufferedReader br_relations = null;
        try {
            String urlR = XMLParse.setR();
            URL objR = new URL(urlR);
            HttpURLConnection conR = (HttpURLConnection) objR.openConnection();

            br_relations = new BufferedReader(new InputStreamReader(conR.getInputStream()));
            Relations[] relations = gson.fromJson(br_relations, Relations[].class);

                for (Relations r : relations) {
                    rootNode.childs.add(new TreeNode(r.getNode()));
                }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (br_relations != null) {
                try {
                    br_relations.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rootNode;
    }


    public void postOrder(TreeNode root) {

        Gson gson = new Gson();
        BufferedReader br_relations = null;
        try {
            String urlR = XMLParse.setR();
            URL objR = new URL(urlR);
            HttpURLConnection conR = (HttpURLConnection) objR.openConnection();

            br_relations = new BufferedReader(new InputStreamReader(conR.getInputStream()));
            Relations[] relations = gson.fromJson(br_relations, Relations[].class);
            long firstLevel = 1;


            if(root !=  null) {
            //System.out.printf("%d \n", root.id);

            BufferedReader br_relatDescr = null;
            BufferedReader br_groups = null;
            BufferedReader br_projects = null;
            BufferedReader br_users = null;

            try {
                String urlRD = XMLParse.setRD();;
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
                RelationDescription[] relDescr = gson.fromJson(br_relatDescr, RelationDescription[].class);
                Groups[] groups = gson.fromJson(br_groups, Groups[].class);
                Projects[] projects = gson.fromJson(br_projects, Projects[].class);
                Users[] users = gson.fromJson(br_users, Users[].class);


                    for (RelationDescription rd : relDescr) {
                        if (rd.getId().equals(root.id)) {
                            //для групп
                            if (rd.getType().equals("Group")){
                                for (Groups gr : groups){
                                    if (rd.getObjectId().equals(gr.getId())){
                                        System.out.println("Group: " + gr.getName());
                                    } } }
                            //для проектов
                            if (rd.getType().equals("Project")){
                                for (Projects pr : projects){
                                    if (rd.getObjectId().equals(pr.getId())){
                                        System.out.println("Project: " + pr.getName());
                                    } } }
                            //для юзеров
                            if (rd.getType().equals("User")){
                                for (Users us : users){
                                    if (rd.getObjectId().equals(us.getId())){
                                        System.out.println("User: " + us.getLogin());
                                        System.out.println(us.getEmail());
                                    } } } } }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br_relatDescr != null) { try { br_relatDescr.close(); } catch (IOException e) { e.printStackTrace(); } }
                if (br_groups != null) { try { br_groups.close(); } catch (IOException e) { e.printStackTrace(); } }
                if (br_projects != null) { try { br_projects.close(); } catch (IOException e) { e.printStackTrace(); } }
                if (br_users != null) { try { br_users.close(); } catch (IOException e) { e.printStackTrace(); } }
            }
        }

            for (Relations r : relations) {
                if (r.getParent().equals(root.id) && r.getLevel().equals(firstLevel)){
                    postOrder(new TreeNode(r.getNode()));
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
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

        TreeNode rootNode = createBinaryTree();
        System.out.println("Using Recursive solution:");

        bi.postOrder(rootNode);

    }
}