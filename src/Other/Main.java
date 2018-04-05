package Other;

import POJO.*;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        BufferedReader br = null;
        BufferedReader br_relations = null;
        BufferedReader br_relatDescr = null;
        BufferedReader br_groups = null;
        BufferedReader br_projects = null;
        BufferedReader br_users = null;

        try {
            br_relations = new BufferedReader(new FileReader("files/relations.json"));
            br_relatDescr = new BufferedReader(new FileReader("files/relatDescr.json"));
            br_groups = new BufferedReader(new FileReader("files/groups.json"));
            br_projects = new BufferedReader(new FileReader("files/projects.json"));
            br_users = new BufferedReader(new FileReader("files/users.json"));

            Relations[] relations = gson.fromJson(br_relations, Relations[].class);
            RelationDescription[] relDescr = gson.fromJson(br_relatDescr, RelationDescription[].class);
            Groups[] groups = gson.fromJson(br_groups, Groups[].class);
            Projects[] projects = gson.fromJson(br_projects, Projects[].class);
            Users[] users = gson.fromJson(br_users, Users[].class);

            //здесь парсим relations
            if (relations != null){
                for (Relations r : relations) {
                    //if (r.getParent() == 7103 && r.getLevel() == 1) {
                        //здесь парсим relatDescription
                        for (RelationDescription rd : relDescr) {
                            if (rd.getId().equals(r.getNode())){
                                //для групп
                                if (rd.getType().equals("Group")){
                                    for (Groups gr : groups){
                                         if (rd.getObjectId().equals(gr.getId())){
                                             System.out.println("id=" + r.getId() + ", parent=" + r.getParent() + ", level=" + r.getLevel());
                                             System.out.println("objectID=" + rd.getObjectId() + ", type=" + rd.getType());
                                             System.out.println("Name of group: " + gr.getName() + "\n");
                                             //grArr.add(gr.getName());
                                         }
                                    }
                                }
                                //для проектов
                                if (rd.getType().equals("Project")){
                                    for (Projects pr : projects){
                                        if (rd.getObjectId().equals(pr.getId())){
                                            System.out.println("id=" + r.getId() + ", parent=" + r.getParent() + ", level=" + r.getLevel());
                                            System.out.println("objectID=" + rd.getObjectId() + ", type=" + rd.getType());
                                            System.out.println("Name of projects: " + pr.getName() + "\n");
                                        }
                                    }
                                }
                                //для юзеров
                                if (rd.getType().equals("User")){
                                    for (Users us : users){
                                        if (rd.getObjectId().equals(us.getId())){
                                            System.out.println("id=" + r.getId() + ", parent=" + r.getParent() + ", level=" + r.getLevel());
                                            System.out.println("objectID=" + rd.getObjectId() + ", type=" + rd.getType());
                                            System.out.println("login=" + us.getLogin() + ", email=" + us.getEmail() + "\n");
                                        }
                                    }
                                }
                            }
                //      }
                    }
                }
            }

            /*System.out.print("Группы=");
            for (int i=0; i<20; i++){
                System.out.print(grArr.get(i));
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br_relatDescr != null) {
                try {
                    br_relatDescr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br_relations != null) {
                try {
                    br_relations.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br_groups != null) {
                try {
                    br_groups.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br_projects != null) {
                try {
                    br_projects.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br_users != null) {
                try {
                    br_users.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
