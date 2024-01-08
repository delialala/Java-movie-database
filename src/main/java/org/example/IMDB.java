package org.example;

import java.awt.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.SwingForms.LoginForm;

import org.pushingpixels.radiance.theming.api.skin.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class IMDB{
    private List<User<?>> users;
    private List <Actor> actors;
    private List <Request> requests;
    private List <Production> productions;
    private static IMDB instance = null;

    public Collection<User<?>> getUsers() {
        return users;
    }


    public List<Request> getRequests() {
        return requests;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    private IMDB(){
        users = new LinkedList<>();
        requests = new LinkedList<>();
        productions = new LinkedList<>();
        actors = new LinkedList<>();
    }

    public static IMDB getInstance(){
        if(instance == null)
            instance = new IMDB();
        return instance;
    }


    public void readJson(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Actor[] actori = mapper.readValue(Paths.get("src\\main\\resources\\input\\actors.json").toFile(), Actor[].class);
            actors = new LinkedList<>(Arrays.asList(actori));

            Production[] productionsArray = mapper.readValue(Paths.get("src\\main\\resources\\input\\production.json").toFile(), Production[].class);
            productions = new LinkedList<>(Arrays.asList(productionsArray));

            Request[] requestsArray = mapper.readValue(Paths.get("src\\main\\resources\\input\\requests.json").toFile(), Request[].class);
            requests = new LinkedList<>(Arrays.asList(requestsArray));

            User<?>[] userArray = mapper.readValue(Paths.get("src\\main\\resources\\input\\accounts.json").toFile(), User[].class);
            users = new LinkedList<>(Arrays.asList(userArray));

        } catch (Exception ex) {
            System.out.println("Probleam reading json!");
            ex.printStackTrace();
        }
        setRequests();
    }

    public User<?> getUser(String username){
        for(User<?> user : IMDB.getInstance().getUsers()){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    public void setRequests() {
        for (Request request : IMDB.getInstance().getRequests())
            if(request.getUsernameResolved().equals("ADMIN"))
                Admin.RequestsHolder.adminRequests.add(request);
            else
                for(User<?> user : users)
                    if (request.getUsernameProblem().equals(user.getUsername()))
                        if(user instanceof Staff<?>)
                            ((Staff<?>) user).getRequestList().add(request);
    }
    public boolean actorExists(String name){
        for(Actor actor : IMDB.getInstance().getActors()){
            if(actor.getname().equals(name))
                return true;
        }
        return false;
    }
    public boolean productionExists(String name){
        for(Production production : IMDB.getInstance().getProductions()){
            if(production.title.equals(name))
                return true;
        }
        return false;
    }
    public Production getProduction(String name){
        for(Production production: productions){
            if(production.title.equals(name))
                return production;
        }
        return null;
    }
    public User<?> getWhoAdded(String name){
        for(User<?> user : users){
            if(user instanceof Staff<?>){
                if(((Staff<?>)user).actorExistsInContributions(name))
                    return user;
                if(((Staff<?>)user).productionExistsInContributions(name))
                    return user;
            }
        }
        return null;
    }
    public Actor getActor(String name){
        for(Actor actor : actors){
            if(actor.name.equals(name))
                return actor;
        }
        return null;
    }
    public void showProductions(List<Production> productions){
        for(Production production : productions){
            production.displayInfo();
        }
    }
    public void showActors(List<Actor> actors){
        for(Actor actor : actors){
            actor.displayInfo();
        }
    }
    public void run(){
        readJson();

    }
    private static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, f);
            }
        }
    }
    public void doSwing(){
        try{
            /*SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try{
                        UIManager.setLookAndFeel(new RadianceAutumnLookAndFeel());
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });*/
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            setUIFont (new javax.swing.plaf.FontUIResource(new Font("MS Mincho",Font.PLAIN, 24)));
            LoginForm form = new LoginForm();
            form.setContentPane(form.mainPanel);
            form.setTitle("IMDB");

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            form.setExtendedState(Frame.MAXIMIZED_BOTH);
            form.setMinimumSize(new Dimension(1920, 1080));
            form.setMaximumSize(new Dimension(1920, 1080));
            form.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        IMDB.getInstance().run();
        //Application app = new Application();
        IMDB.getInstance().doSwing();

    }
}
