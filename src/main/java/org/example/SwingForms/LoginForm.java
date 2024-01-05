package org.example.SwingForms;

import org.example.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class LoginForm extends JFrame{
    public JPanel mainPanel;
    private JPanel loginPage;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JPanel loginBox;
    private JButton loginButton;
    private JPanel mainPage;
    private JPanel titleBar;
    private JButton searchButton;
    private JComboBox menuBox;
    private JTextField textField2;
    private JPanel bigPanel;
    private JPanel listPanel;
    private JPanel listHolder;
    private JScrollPane scroller;
    private JPanel scrollerPanel;
    private JPanel productionDetailsPanel;
    private JButton backButton;
    private JTextArea reviewTextArea;
    private JScrollPane reviewScroll;
    private JButton postReviewButton;
    private JButton addToFavoritesButton;
    private JPanel settingsPanel;
    private JRadioButton productionsRadioButton;
    private JRadioButton actorsRadioButton;
    private JRadioButton sortAlphabeticallyRadioButton;
    private JPanel sexyActorSettings;
    private JSpinner actorMinReviews;
    private JPanel sexyProductionSettings;
    private JComboBox genreBox;
    private JComboBox seriesOrProductionsBox;
    private JSpinner actorMaxReviews;
    private JSpinner actorMinPerformances;
    private JSpinner actorMaxPerformances;
    private JSpinner productionMinReviews;
    private JSpinner productionMaxReviews;
    private JSpinner productionMinRating;
    private JSpinner productionMaxRating;
    private JLabel ratingLabel;
    private JLabel ratingLabel2;
    private JButton sortButton;
    private JSpinner ratingSpinner;
    private JLabel currentTabTitle;
    private JLabel scoreLabel;
    private JPanel reviewPanel;
    private JLabel imageInfo;
    private JLabel descriptionLabel;
    private JLabel titleLabel;
    private JTextArea description;
    private JScrollPane ratingsScroller;
    private JPanel ratingsPanel;
    private User<?> currentUser;
    private Production crtProduction;
    private Actor crtActor;
    public LoginForm() {
        scrollerPanel.setLayout(new FlowLayout());
        mainPanel.add("MainPage", mainPage);
        loadProductions(((LinkedList<Production>)IMDB.getInstance().getProductions()));
        ButtonGroup actorsOrProductions = new ButtonGroup();
        actorsOrProductions.add(productionsRadioButton);
        actorsOrProductions.add(actorsRadioButton);

        ratingsPanel.setLayout(new GridLayout(0, 1));
        //scrollerPanel.setLayout(new GridLayout());

        actorMinReviews.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        actorMaxReviews.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        actorMinPerformances.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        actorMaxPerformances.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        productionMinReviews.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        productionMaxReviews.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        productionMinRating.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        productionMinReviews.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        productionMaxRating.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        ratingSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        productionsRadioButton.setSelected(true);
        genreBox.setModel(new DefaultComboBoxModel<>(Genre.values()));
        seriesOrProductionsBox.addItem("Any");
        seriesOrProductionsBox.addItem("Movies");
        seriesOrProductionsBox.addItem("Series");
        genreBox.setSelectedItem(Genre.Any);
        menuBox.addItem("Log out");

        description = setTextAreaSettings(description);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");

            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                scrollerPanel.removeAll();
                if(productionsRadioButton.isSelected()){
                    currentTabTitle.setText("Productions");
                    loadProductions(getSortedProductions());
                }
                else{
                    currentTabTitle.setText("Actors");
                    loadActors(getSortedActors());
                }
                scrollerPanel.revalidate();
                scrollerPanel.repaint();
            }
        });
        postReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(postReviewButton.getText().equals("Post review")){
                    for(Rating rating : crtProduction.getRatings()){
                        if(rating.getUsername().equals(currentUser.getUsername())){
                            JOptionPane.showMessageDialog(new JFrame(), "Sorry, you have already left a review on this production! You can try deleting your review and adding another one!");
                            return;
                        }
                    }
                    String comment = reviewTextArea.getText();
                    if(comment.isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Please enter a comment to go with your review!");
                        return;
                    }
                    int rating = (int)ratingSpinner.getValue();

                    Rating newRating = new Rating();
                    newRating.setUsername(currentUser.getUsername());
                    newRating.setRating(rating);
                    newRating.setComment(comment);

                    reviewTextArea.setEditable(false);
                    reviewTextArea.setFocusable(false);
                    ratingSpinner.setEnabled(false);
                    crtProduction.notifyUsers(crtProduction.getUpdate(newRating));
                    crtProduction.addRating(newRating);
                    postReviewButton.setText("Remove review");

                    ratingsPanel.removeAll();
                    loadRatings(crtProduction);

                    ratingsPanel.revalidate();
                    ratingsPanel.repaint();
                }
                else{

                    crtProduction.removeRating(currentUser.whatRatingTheyveLeft(crtProduction));

                    ratingsPanel.removeAll();
                    loadRatings(crtProduction);

                    ratingsPanel.revalidate();
                    ratingsPanel.repaint();
                    postReviewButton.setText("Post review");
                    reviewTextArea.setText("");
                    ratingSpinner.setValue(0);

                    reviewTextArea.setEditable(true);
                    reviewTextArea.setFocusable(true);
                    ratingSpinner.setEnabled(true);
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String email = emailField.getText();

                StringBuilder temp = new StringBuilder();
                temp.append(passwordField1.getPassword());
                String password = temp.toString();

                Collection<User<?>> users = IMDB.getInstance().getUsers();
                boolean userExists = false;
                for(User<?> user : users){
                    if(email.equals(user.getInformation().getCredentials().getEmail())
                            && password.equals(user.getInformation().getCredentials().getPassword()))
                    {
                        userExists = true;
                        currentUser = user;
                    }
                }
                if(userExists){
                    ((CardLayout)mainPanel.getLayout()).show(mainPanel, "MainPage");
                    menuBox.addItem(currentUser.getUsername());
                    menuBox.setSelectedItem(currentUser.getUsername());
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Your credentials are wrong, please try again!");
                }
            }
        });
        addToFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(addToFavoritesButton.getText().equals("Add to favorites")){
                    if(crtActor == null){
                        currentUser.addFavorite(crtProduction.title);
                    }
                    if(crtProduction == null){
                        currentUser.addFavorite(crtActor.name);
                    }
                    addToFavoritesButton.setText("Remove from favorites");
                }else{
                    if(crtActor == null){
                        currentUser.removeFavorite(crtProduction.title);
                    }
                    if(crtProduction == null){
                        currentUser.removeFavorite(crtActor.name);
                    }
                    addToFavoritesButton.setText("Add to favorites");
                }
            }
        });

        menuBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println((String)menuBox.getSelectedItem());
                if(((String)menuBox.getSelectedItem()).equals("Log out"))
                    ((CardLayout)mainPanel.getLayout()).show(mainPanel, "loginCard");

                menuBox.setSelectedItem(currentUser.getUsername());
            }
        });
    }

    private LinkedList<Production> getSortedProductions(){
        List<Production> filteredList = IMDB.getInstance().getProductions();
        if(genreBox.getSelectedItem() != Genre.Any){
            filteredList = filteredList.
                    stream().filter(p -> p.isGenreIncluded((Genre)genreBox.getSelectedItem())).toList();
        }
        if(seriesOrProductionsBox.getSelectedItem() != "Any"){
            if(seriesOrProductionsBox.getSelectedItem() == "Movies")
                filteredList = filteredList.
                        stream().filter(p -> p.isMovieOrSeries()).toList();
            else
                filteredList = filteredList.
                        stream().filter(p -> !(p.isMovieOrSeries())).toList();
        }
        filteredList = filteredList.
                stream().filter(p -> p.getRatings().size() > (int)productionMinReviews.getValue()).toList();

        if((int)productionMaxReviews.getValue() != 0){
            filteredList = filteredList.
                    stream().filter(p -> p.getRatings().size() < (int)productionMaxReviews.getValue()).toList();
        }
        filteredList = filteredList.
                stream().filter(p -> p.getAverageRating() > (int)productionMinRating.getValue()).toList();

        if((int)productionMaxRating.getValue() != 0){
            filteredList = filteredList.
                    stream().filter(p -> p.getAverageRating() < (int)productionMaxRating.getValue()).toList();
        }
        return new LinkedList<>(filteredList);
    }
    private LinkedList<Actor> getSortedActors(){
        List<Actor> filteredList = IMDB.getInstance().getActors();
        filteredList = filteredList.
                stream().filter(a -> a.getPerformances().size() > (int)actorMinPerformances.getValue()).toList();

        if((int)actorMaxPerformances.getValue() != 0){
            filteredList = filteredList.
                    stream().filter(a -> a.getPerformances().size()  < (int)actorMaxPerformances.getValue()).toList();
        }
        TreeSet<Actor> temp;
        if(sortAlphabeticallyRadioButton.isSelected()){
            temp = new TreeSet<>(filteredList);
            filteredList = new ArrayList<>(temp);
        }
        return new LinkedList<Actor>(filteredList);
    }
    private void loadProductions(LinkedList<Production> productions){
        ArrayList<JLabel> images = new ArrayList<>();
        for(Production production : productions){
            String path = "src\\main\\resources\\images\\" + production.title + ".jpg";
            ImageIcon icon = null;

            try{
                Path tryPath = Paths.get(path);
                if(!Files.exists(tryPath))
                    throw new Exception();
                icon = new ImageIcon(path);
            } catch(Exception e){
                icon = new ImageIcon("src\\main\\resources\\images\\substitute.jpg");

            }

            JLabel newLabel = new JLabel();
            ImageIcon finalIcon = icon;

            newLabel.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                {
                    reviewPanel.setVisible(true);
                    ((CardLayout)listHolder.getLayout()).show(listHolder, "productionDetailsCard");
                    Image image = finalIcon.getImage();
                    Image newImg = image.getScaledInstance(200, 300, Image.SCALE_FAST);
                    imageInfo.setIcon(new ImageIcon(newImg));
                    titleLabel.setText(production.title);
                    description.setText(production.toString());
                    loadRatings(production);
                    crtProduction = production;
                    crtActor = null;
                    Rating rating = currentUser.whatRatingTheyveLeft(crtProduction);
                    if(currentUser.whatRatingTheyveLeft(crtProduction) != null){
                        reviewTextArea.setText(rating.getComment());
                        ratingSpinner.setValue(rating.getRating());
                        postReviewButton.setText("Remove review");

                        reviewTextArea.setEditable(false);
                        reviewTextArea.setFocusable(false);
                        ratingSpinner.setEnabled(false);

                    }
                    else{
                        reviewTextArea.setText("");
                        ratingSpinner.setValue(0);
                        postReviewButton.setText("Post review");

                        reviewTextArea.setEditable(true);
                        reviewTextArea.setFocusable(true);
                        ratingSpinner.setEnabled(true);
                    }
                    if(currentUser.isInFavorites(crtProduction.title))
                        addToFavoritesButton.setText("Remove from favorites");
                    else
                        addToFavoritesButton.setText("Add to favorites");

                }
            });
            newLabel.setIcon(icon);
            newLabel.setText(production.title);
            newLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            newLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            newLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            scrollerPanel.add(newLabel);
        }
    }
    private void loadActors(LinkedList<Actor> actors){
        ArrayList<JLabel> images = new ArrayList<>();
        for(Actor actor : actors){
            String path = "src\\main\\resources\\images\\" + actor.name + ".jpg";
            ImageIcon icon = null;

            try{
                Path tryPath = Paths.get(path);
                if(!Files.exists(tryPath))
                    throw new Exception();
                icon = new ImageIcon(path);
            } catch(Exception e){
                icon = new ImageIcon("src\\main\\resources\\images\\substitute.jpg");

            }

            JLabel newLabel = new JLabel();


            ImageIcon finalIcon = icon;
            newLabel.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                {
                    reviewPanel.setVisible(false);
                    ((CardLayout)listHolder.getLayout()).show(listHolder, "productionDetailsCard");

                    Image image = finalIcon.getImage();
                    Image newImg = image.getScaledInstance(300, 300, Image.SCALE_FAST);
                    imageInfo.setIcon(new ImageIcon(newImg));
                    crtActor = actor;
                    crtProduction = null;
                    if(currentUser.isInFavorites(crtActor.name))
                        addToFavoritesButton.setText("Remove from favorites");
                    else
                        addToFavoritesButton.setText("Add to favorites");
                }
            });
            newLabel.setIcon(icon);
            newLabel.setText(actor.name);
            newLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            newLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            newLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            scrollerPanel.add(newLabel);
        }
    }
    private void loadRatings(Production production){
        for(Rating rating : production.getRatings()){
            JTextArea ratingTextArea = setTextAreaSettings(new JTextArea());
            ratingTextArea.setText(rating.toString());
            ratingsPanel.add(ratingTextArea);
        }
    }
    private JTextArea setTextAreaSettings(JTextArea textArea){
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(new Color(214, 217, 223));
        textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setBorder(UIManager.getBorder("Label.border"));
        return textArea;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
