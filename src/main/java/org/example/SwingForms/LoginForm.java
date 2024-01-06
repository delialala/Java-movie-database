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
    private JPanel ProfilePanel;
    private JButton profileBackButton;
    private JLabel profileName;
    private JRadioButton movieIssueRadioButton;
    private JRadioButton actorIssueRadioButton;
    private JRadioButton deleteAccountRadioButton;
    private JRadioButton othersRadioButton;
    private JComboBox requestBox;
    private JButton seeRequestsButton;
    private JTextArea profileDescriptionText;
    private JPanel notificationsPanel;
    private JButton postRequestButton;
    private JTextArea requestText;
    private JButton pendingRequestsButton;
    private JPanel manageDatabasePanel;
    private JPanel editorHolder;
    private JRadioButton addProductionRadioButton;
    private JRadioButton deleteProductionRadioButton;
    private JRadioButton modifyProductionRadioButton;
    private JButton backDatabaseButton;
    private JRadioButton addActorRadioButton;
    private JRadioButton deleteActorRadioButton;
    private JRadioButton modifyActorRadioButton;
    private JRadioButton addUserRadioButton;
    private JRadioButton deleteUserRadioButton;
    private JComboBox comboBox1;
    private JPanel productionEditPanel;
    private JPanel actorEditPanel;
    private JPanel userEditPanel;
    private JPanel seriesOrMovieHolder;
    private JPanel moviePanel;
    private JPanel seriesPanel;
    private JButton submitButton;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JButton addDirectorButton;
    private JButton deleteDirectorButton;
    private JSpinner spinner1;
    private JTextArea textArea1;
    private User<?> currentUser;
    private Production crtProduction;
    private Actor crtActor;
    private ArrayList<Request> userRequests = new ArrayList<>();
    public LoginForm() {
        scrollerPanel.setLayout(new FlowLayout());
        mainPanel.add("MainPage", mainPage);
        loadProductions(((LinkedList<Production>)IMDB.getInstance().getProductions()));
        ButtonGroup actorsOrProductions = new ButtonGroup();
        actorsOrProductions.add(productionsRadioButton);
        actorsOrProductions.add(actorsRadioButton);

        ratingsPanel.setLayout(new GridLayout(0, 1));
        notificationsPanel.setLayout(new GridLayout(0, 1));
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
        requestBox.setModel(new DefaultComboBoxModel<String>(getProductionsNameArray(IMDB.getInstance().getProductions()).toArray(new String[0])));
        seriesOrProductionsBox.addItem("Any");
        seriesOrProductionsBox.addItem("Movies");
        seriesOrProductionsBox.addItem("Series");
        genreBox.setSelectedItem(Genre.Any);

        description = setTextAreaSettings(description);
        profileDescriptionText = setTextAreaSettings(profileDescriptionText);
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
                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
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
                    ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");

                    setUserRequests();
                    menuBox.addItem(currentUser.getUsername());

                    ArrayList<String> menuOptions = new ArrayList<>();

                    menuOptions.add(currentUser.getUsername());
                    menuOptions.add("Profile and requests");
                    menuOptions.add("Log out");

                    if(currentUser instanceof Staff<?>){
                        menuOptions.add("Manage database");
                        pendingRequestsButton.setVisible(true);
                        if(currentUser instanceof Admin<?>)
                            menuOptions.add("Admin requests");
                    }
                    else{
                        pendingRequestsButton.setVisible(false);
                    }
                    menuBox.setModel(new DefaultComboBoxModel<String>(menuOptions.toArray(new String[0])));
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
                if(((String)menuBox.getSelectedItem()).equals("Log out")){
                    ((CardLayout)mainPanel.getLayout()).show(mainPanel, "loginCard");
                    emailField.setText("");
                    passwordField1.setText("");
                }

                if(((String)menuBox.getSelectedItem()).equals("Profile and requests")){
                    ((CardLayout)listHolder.getLayout()).show(listHolder, "profileCard");
                    profileName.setText(currentUser.getUsername());
                    profileDescriptionText.setText(currentUser.toString());

                    notificationsPanel.removeAll();
                    loadNotifications();

                    notificationsPanel.revalidate();
                    notificationsPanel.repaint();
                }
                if(((String)menuBox.getSelectedItem()).equals("Admin requests")){
                    ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
                    scrollerPanel.removeAll();

                    if(Admin.RequestsHolder.adminRequests.isEmpty()){
                        JLabel label = new JLabel("No requests here!");
                        scrollerPanel.add(label);
                    }
                    loadAdminRequests(Admin.RequestsHolder.adminRequests);

                    scrollerPanel.revalidate();
                    scrollerPanel.repaint();
                }
                if(((String)menuBox.getSelectedItem()).equals("Manage database")) {
                    ((CardLayout)listHolder.getLayout()).show(listHolder, "manageDatabaseCard");
                }
                    menuBox.setSelectedItem(currentUser.getUsername());
            }
        });
        profileBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
            }
        });
        othersRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                requestBox.setModel(new DefaultComboBoxModel<String>());
                requestBox.setEnabled(false);
            }
        });
        movieIssueRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                requestBox.setModel(new DefaultComboBoxModel<String>(getProductionsNameArray(IMDB.getInstance().getProductions()).toArray(new String[0])));
                requestBox.setEnabled(true);
            }
        });
        actorIssueRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                requestBox.setModel(new DefaultComboBoxModel<String>(getActorsNameArray(IMDB.getInstance().getActors()).toArray(new String[0])));
                requestBox.setEnabled(true);
            }
        });
        deleteAccountRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                requestBox.setModel(new DefaultComboBoxModel<String>());
                requestBox.setEnabled(false);
            }
        });
        postRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Request request = new Request();
                RequestType type = null;
                String text = requestText.getText();
                if(text.isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter a comment to go with your request!");
                    return;
                }
                if(deleteAccountRadioButton.isSelected() || othersRadioButton.isSelected()){
                    Admin.RequestsHolder.adminRequests.add(request);
                    request.setUsernameResolved("ADMIN");
                    // add the entire team of admins as observers
                    for(User<?> user : IMDB.getInstance().getUsers()){
                        if(user instanceof Admin<?>)
                            request.addObserver(user);
                    }
                    if(deleteAccountRadioButton.isSelected())
                        type = RequestType.DELETE_ACCOUNT;
                    if(othersRadioButton.isSelected())
                        type = RequestType.OTHERS;
                }
                else {
                    User<?> userResolve = null;
                    if (actorIssueRadioButton.isSelected()) {
                        String name = (String)requestBox.getSelectedItem();
                        request.setActorName(name);
                        userResolve = IMDB.getInstance().getWhoAdded(name);
                        type = RequestType.ACTOR_ISSUE;
                    }

                    if (movieIssueRadioButton.isSelected()) {
                        String name = (String)requestBox.getSelectedItem();
                        userResolve = IMDB.getInstance().getWhoAdded(name);
                        request.setActorName(name);
                        type = RequestType.MOVIE_ISSUE;
                    }
                    ((Staff<?>) userResolve).getRequestList().add(request);

                    request.setUsernameResolved(userResolve.getUsername());
                    request.addObserver(userResolve);
                }

                request.setType(type);
                if(requestText.getText().isEmpty()){

                }
                request.setDescription(text);
                request.setUsernameProblem(currentUser.getUsername());
                requestText.setText("");
                IMDB.getInstance().getRequests().add(request);

                setUserRequests();
                request.notifyUsers("You have a new request from the user: " + currentUser.getUsername());

            }
        });
        seeRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
                scrollerPanel.removeAll();

                if(userRequests.isEmpty()){
                    JLabel label = new JLabel("No requests here!");
                    scrollerPanel.add(label);
                }
                loadRequests(userRequests);

                scrollerPanel.revalidate();
                scrollerPanel.repaint();
            }
        });
        pendingRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
                scrollerPanel.removeAll();

                if(((Staff<?>)currentUser).getRequestList().isEmpty()){
                    JLabel label = new JLabel("No requests here!");
                    scrollerPanel.add(label);
                }
                loadPendingRequests(((Staff<?>)currentUser).getRequestList());

                scrollerPanel.revalidate();
                scrollerPanel.repaint();
            }
        });
        backDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
            }
        });
    }
    public void setUserRequests() {
        userRequests = new ArrayList<>();

        for (Request request : IMDB.getInstance().getRequests()) {
            if (request.getUsernameProblem().equals(currentUser.getUsername())) {
                userRequests.add(request);
            }
        }
    }
    private void loadAdminRequests(List<Request> requests){
        for(Request request : requests){
            JPanel temp = new JPanel(new GridBagLayout());

            JButton resolveRequestButton = new JButton();
            resolveRequestButton.setText("Mark as resolved");

            JTextArea textArea = setTextAreaSettings(new JTextArea());
            textArea.setPreferredSize(new Dimension(1000, 300));
            textArea.setText(request.toString());

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            temp.add(resolveRequestButton, c);

            c.gridx = 0;
            temp.add(textArea, c);


            resolveRequestButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    IMDB.getInstance().getRequests().remove(request);
                    Admin.RequestsHolder.adminRequests.remove(request);

                    resolveRequestButton.setEnabled(false);
                    resolveRequestButton.setText("Request resolved!");
                }
            });

            scrollerPanel.add(temp, BorderLayout.CENTER);
            scrollerPanel.add(new JSeparator());
        }
    }
    private void loadPendingRequests(List<Request> requests){
        for(Request request : requests){
            JPanel temp = new JPanel(new GridBagLayout());

            JButton deleteButton = new JButton();
            deleteButton.setText("Reject request");

            JButton resolveRequestButton = new JButton();
            resolveRequestButton.setText("Mark as resolved");

            JTextArea textArea = setTextAreaSettings(new JTextArea());
            textArea.setPreferredSize(new Dimension(1000, 300));
            textArea.setText(request.toString());

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            temp.add(deleteButton, c);

            c.gridx = 2;
            temp.add(resolveRequestButton, c);

            c.gridx = 0;
            temp.add(textArea, c);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ((Staff<?>) currentUser).getRequestList().remove(request);
                    IMDB.getInstance().getRequests().remove(request);

                    deleteButton.setEnabled(false);
                    resolveRequestButton.setEnabled(false);
                    deleteButton.setText("Request rejected!");
                }
            });

            resolveRequestButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ((Staff<?>) currentUser).resolveRequest(request);

                    resolveRequestButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    resolveRequestButton.setText("Request resolved!");
                }
            });

            scrollerPanel.add(temp, BorderLayout.CENTER);
            scrollerPanel.add(new JSeparator());
        }
    }
    private void loadRequests(List<Request> requests){
        for(Request request : requests){
            JPanel temp = new JPanel(new GridBagLayout());

            JButton deleteButton = new JButton();
            deleteButton.setText("Delete request");

            JTextArea textArea = setTextAreaSettings(new JTextArea());
            textArea.setPreferredSize(new Dimension(1000, 300));
            textArea.setText(request.toString());

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            temp.add(deleteButton, c);

            c.gridx = 0;

            temp.add(textArea, c);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    IMDB.getInstance().getRequests().remove(request);
                    Staff<?> userResolve = ((Staff<?>)IMDB.getInstance().getUser(request.getUsernameResolved()));
                    if(userResolve == null){
                        Admin.RequestsHolder.adminRequests.remove(request);
                    }
                    else{
                        userResolve.getRequestList().remove(request);
                    }

                    deleteButton.setEnabled(false);
                    deleteButton.setText("Request deleted!");
                    setUserRequests();
                }
            });

            scrollerPanel.add(temp, BorderLayout.CENTER);
            scrollerPanel.add(new JSeparator());
        }
    }
    private void loadNotifications(){
        for(String notification: currentUser.getNotifications()){
            JTextArea notificationTextArea = setTextAreaSettings(new JTextArea());
            notificationTextArea.setText(notification);
            notificationsPanel.add(notificationTextArea);
        }
    }
    private ArrayList<String> getProductionsNameArray(List<Production> productions){
        ArrayList<String> productionNames = new ArrayList<>();
        for(Production production : productions)
            productionNames.add(production.title);
        return productionNames;
    }
    private ArrayList<String> getActorsNameArray(List<Actor> actors){
        ArrayList<String> actorNames = new ArrayList<>();
        for(Actor actor : actors)
            actorNames.add(actor.name);
        return actorNames;
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
