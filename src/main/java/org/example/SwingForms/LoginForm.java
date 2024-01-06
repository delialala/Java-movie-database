package org.example.SwingForms;

import org.example.*;
import org.example.ApplicationStates.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private JComboBox entitiesComboBox;
    private JPanel productionEditPanel;
    private JPanel actorEditPanel;
    private JPanel userEditPanel;
    private JPanel seriesOrMovieHolder;
    private JPanel moviePanel;
    private JPanel seriesPanel;
    private JButton submitButton;
    private JTextField passwordTextField;
    private JComboBox directorsComboBox;
    private JTextField directorTextField;
    private JButton addDirectorButton;
    private JButton deleteDirectorButton;
    private JSpinner releaseYearSpinner;
    private JTextArea plotTextArea;
    private JButton addActorButton;
    private JButton addGenreButton;
    private JButton deleteActorButton;
    private JButton deleteGenreButton;
    private JComboBox actorsComboBox;
    private JComboBox genresComboBox;
    private JTextField actorTextField;
    private JTextField genreTextField;
    private JTextField titleTextField;
    private JComboBox seasonsComboBox;
    private JTextField seasonNameTextField;
    private JComboBox episodesComboBox;
    private JTextField episodeTextField;
    private JButton addEpisodeButton;
    private JButton deleteEpisodeButton;
    private JTextField durationTextField;
    private JTextField actorNameTextField;
    private JComboBox performancesComboBox;
    private JTextField performanceTextField;
    private JTextArea biographyTextArea;
    private JComboBox performanceTypeComboBox;
    private JButton submitButon;
    private JComboBox userTypeComboBox;
    private JTextField mailTextField;
    private JTextField nameTextField;
    private JTextField countryTextField;
    private JTextField birthdateTextField;
    private JTextField usernameTextField;
    private JRadioButton movieRadioButton;
    private JRadioButton seriesRadioButton;
    private JTextField epDurationTextField;
    private JButton addSeasonButton;
    private JButton deleteSeasonButton;
    private JButton addPerformanceButton;
    private JButton deletePerformanceButton;
    private JSpinner ageSpinner;
    private JTextField genderTextField;
    private JPanel requestPanel;
    private User<?> currentUser;
    private Production crtProduction;
    private Actor crtActor;
    private ArrayList<Request> userRequests = new ArrayList<>();
    private ArrayList<String> directorList = new ArrayList<>();
    private ArrayList<String> actorList = new ArrayList<>();
    private ArrayList<Genre> genreList = new ArrayList<>();
    private Map<String, List<Episode>> episodeDistribution = new HashMap<>();
    List<Pair<String, Actor.Type>> performances = new ArrayList<>();

    public LoginForm() {
        // declarations:
        scrollerPanel.setLayout(new FlowLayout());
        mainPanel.add("MainPage", mainPage);
        loadProductions(((LinkedList<Production>)IMDB.getInstance().getProductions()));
        ButtonGroup actorsOrProductions = new ButtonGroup();
        actorsOrProductions.add(productionsRadioButton);
        actorsOrProductions.add(actorsRadioButton);

        ratingsPanel.setLayout(new GridLayout(0, 1));
        notificationsPanel.setLayout(new GridLayout(0, 1));

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
        releaseYearSpinner.setModel(new SpinnerNumberModel(0, 0, 2024, 1));
        ageSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));

        productionsRadioButton.setSelected(true);
        addProductionRadioButton.setSelected(true);
        movieRadioButton.setSelected(true);

        genreBox.setModel(new DefaultComboBoxModel<>(Genre.values()));
        requestBox.setModel(new DefaultComboBoxModel<String>(getProductionsNameArray(IMDB.getInstance().getProductions()).toArray(new String[0])));
        entitiesComboBox.setModel(new DefaultComboBoxModel<String>(getProductionsNameArray(IMDB.getInstance().getProductions()).toArray(new String[0])));
        performanceTypeComboBox.setModel(new DefaultComboBoxModel(Actor.Type.values()));

        seriesOrProductionsBox.addItem("Any");
        seriesOrProductionsBox.addItem("Movies");
        seriesOrProductionsBox.addItem("Series");
        userTypeComboBox.addItem("Regular");
        userTypeComboBox.addItem("Admin");
        userTypeComboBox.addItem("Contributor");

        genreBox.setSelectedItem(Genre.Any);

        description = setTextAreaSettings(description);
        profileDescriptionText = setTextAreaSettings(profileDescriptionText);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currentTabTitle.setText("Productions");

                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");
                ratingsPanel.removeAll();
                loadRatings(crtProduction);

                ratingsPanel.revalidate();
                ratingsPanel.repaint();

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
                    currentTabTitle.setText("Productions");
                    setUserRequests();
                    menuBox.addItem(currentUser.getUsername());

                    ArrayList<String> menuOptions = new ArrayList<>();

                    menuOptions.add(currentUser.getUsername());
                    menuOptions.add("Profile and requests");
                    menuOptions.add("Log out");

                    if(currentUser instanceof Staff<?>){
                        menuOptions.add("Manage database");
                        pendingRequestsButton.setVisible(true);
                        if(currentUser instanceof Admin<?>){
                            menuOptions.add("Admin requests");
                            addUserRadioButton.setVisible(true);
                            deleteUserRadioButton.setVisible(true);
                            requestPanel.setVisible(false);
                            seeRequestsButton.setVisible(false);
                        }else{
                            addUserRadioButton.setVisible(false);
                            deleteUserRadioButton.setVisible(false);
                            requestPanel.setVisible(true);
                            seeRequestsButton.setVisible(true);
                        }
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
                    currentTabTitle.setText("Profile");
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
                    currentTabTitle.setText("Admin requests");

                    scrollerPanel.revalidate();
                    scrollerPanel.repaint();
                }
                if(((String)menuBox.getSelectedItem()).equals("Manage database")) {
                    currentTabTitle.setText("Manage database");
                    entitiesComboBox.setModel(new DefaultComboBoxModel<String>(((Staff<?>)currentUser).getProductionsContribution().toArray(new String[0])));

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
                currentTabTitle.setText("Requests");

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
                currentTabTitle.setText("Requests");

                scrollerPanel.revalidate();
                scrollerPanel.repaint();
            }
        });
        backDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadProductions((LinkedList<Production>) IMDB.getInstance().getProductions());

                ((CardLayout)listHolder.getLayout()).show(listHolder, "scrollerCard");

                scrollerPanel.revalidate();
                scrollerPanel.repaint();
                currentTabTitle.setText("Productions");
            }
        });
        addProductionRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "productionCard");
                setPanelEnabled(productionEditPanel, true);
                entitiesComboBox.setModel(new DefaultComboBoxModel<String>());
                clearProduction();
                movieRadioButton.setEnabled(true);
                seriesRadioButton.setEnabled(true);
            }
        });
        deleteProductionRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearProduction();
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "productionCard");
                setPanelEnabled(productionEditPanel, false);
                entitiesComboBox.setModel(new DefaultComboBoxModel<String>(getProductionsNameArray(IMDB.getInstance().getProductions()).toArray(new String[0])));
            }
        });
        modifyProductionRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "productionCard");
                setPanelEnabled(productionEditPanel, true);
                entitiesComboBox.setModel(new DefaultComboBoxModel<String>(getProductionsNameArray(IMDB.getInstance().getProductions()).toArray(new String[0])));
                setCurrentProduction(IMDB.getInstance().getProduction((String)entitiesComboBox.getSelectedItem()));
                movieRadioButton.setEnabled(false);
                seriesRadioButton.setEnabled(false);
            }
        });
        addActorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "actorCard");
                setPanelEnabled(actorEditPanel, true);
                entitiesComboBox.setModel(new DefaultComboBoxModel<String>());
                clearActor();
            }
        });
        deleteActorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearActor();
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "actorCard");
                setPanelEnabled(actorEditPanel, false);
                entitiesComboBox.setModel(new DefaultComboBoxModel<>(getActorsNameArray(IMDB.getInstance().getActors()).toArray(new String[0])));
            }
        });
        modifyActorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "actorCard");
                setPanelEnabled(actorEditPanel, true);
                entitiesComboBox.setModel(new DefaultComboBoxModel<>(getActorsNameArray(IMDB.getInstance().getActors()).toArray(new String[0])));
                setCurrentActor(IMDB.getInstance().getActor((String)entitiesComboBox.getSelectedItem()));
            }
        });
        addUserRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "userCard");
                setPanelEnabled(userEditPanel, true);
                entitiesComboBox.setModel(new DefaultComboBoxModel<String>());
            }
        });
        deleteUserRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)editorHolder.getLayout()).show(editorHolder, "userCard");
                setPanelEnabled(userEditPanel, false);
                List<User<?>> users = (List<User<?>>)IMDB.getInstance().getUsers();
                entitiesComboBox.setModel(new DefaultComboBoxModel<String>(getUserNameArray(users).toArray(new String[0])));

            }
        });
        entitiesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(modifyProductionRadioButton.isSelected()){
                    setCurrentProduction(IMDB.getInstance().getProduction((String)entitiesComboBox.getSelectedItem()));
                }
                if(modifyActorRadioButton.isSelected()){
                    setCurrentActor(IMDB.getInstance().getActor((String)entitiesComboBox.getSelectedItem()));
                }
            }
        });
        addDirectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String director = directorTextField.getText();
                if(director.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                    return;
                }
                directorList.add(director);
                directorsComboBox.addItem(director);

            }
        });
        addActorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String actor = actorTextField.getText();
                if(actor.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                    return;
                }
                actorList.add(actor);
                actorsComboBox.addItem(actor);
                }
            });
        addGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String genreText = genreTextField.getText();
                Genre genre = null;
                try{
                    genre = Genre.valueOf(genreText);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid genre!");
                    return;
                }
                genreList.add(genre);
                genresComboBox.addItem(genreText);
            }
        }
        );
        movieRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)seriesOrMovieHolder.getLayout()).show(seriesOrMovieHolder, "movieCard");
            }
        });
        seriesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((CardLayout)seriesOrMovieHolder.getLayout()).show(seriesOrMovieHolder, "seriesCard");

            }
        });
        deleteDirectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String director = (String)directorsComboBox.getSelectedItem();
                directorList.remove(director);
                directorsComboBox.removeItem(director);
                if(modifyProductionRadioButton.isSelected()){
                    crtProduction.setDirectors(directorList);
                }
            }
        });


        seasonsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList <Episode> episodes = (ArrayList<Episode>) episodeDistribution.get(seasonsComboBox.getSelectedItem());
                ArrayList <String> episodeNames = new ArrayList<>();
                if(episodes != null){
                    for(Episode episode : episodes){
                        episodeNames.add(episode.getEpisodeName());
                    }
                    episodesComboBox.setModel(new DefaultComboBoxModel(episodeNames.toArray(new String[0])));
                }
                else{
                    episodesComboBox.setModel(new DefaultComboBoxModel());
                }

            }
        });

        deleteActorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String actor = (String)actorsComboBox.getSelectedItem();
                actorsComboBox.removeItem(actor);
                actorList.remove(actor);

            }
        });
        deleteGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Genre genre = null;
                if(!((String)genresComboBox.getSelectedItem()).isEmpty()){
                    genre = Genre.valueOf((String)genresComboBox.getSelectedItem());
                    genresComboBox.removeItem(genre.toString());
                    genreList.remove(genre);
                }
            }
        });
        submitButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Production production = null;
                if(addProductionRadioButton.isSelected()){
                    if(movieRadioButton.isSelected()){
                        production = new Movie();
                        if(durationTextField.getText().isEmpty()){
                            JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                            return;
                        }
                    }
                    if(seriesRadioButton.isSelected()){
                        production = new Series();
                    }
                    production.setTitle("");
                    production.addObserver(currentUser);
                    ((Staff)currentUser).addProductionSystem(production);
                }
                if(modifyProductionRadioButton.isSelected()){
                    production = crtProduction;
                }

                if(addProductionRadioButton.isSelected() || modifyProductionRadioButton.isSelected()) {
                    if(titleTextField.getText().isEmpty()
                            || plotTextArea.getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                        return;
                    }
                    production.setTitle(titleTextField.getText());
                    System.out.println(titleTextField.getText() + "asdfasdfasdf");
                    production.setDirectors(directorList);
                    production.setActors(actorList);
                    production.setGenres(genreList);
                    production.setPlot(plotTextArea.getText());
                    if(production instanceof Movie){
                        ((Movie)production).setReleaseYear((int)releaseYearSpinner.getValue());
                        ((Movie)production).setDuration(durationTextField.getText());
                    }
                    if(production instanceof Series){
                        ((Series)production).setReleaseYear((int)releaseYearSpinner.getValue());
                        ((Series)production).setEpisodeDistribution(episodeDistribution);
                    }
                    JOptionPane.showMessageDialog(new JFrame(), "Added or modified production!");
                }
                if(deleteActorRadioButton.isSelected()){
                    ((Staff<?>) currentUser).removeActorSystem((String)entitiesComboBox.getSelectedItem());
                    entitiesComboBox.removeItem((String)entitiesComboBox.getSelectedItem());
                }
                if(deleteProductionRadioButton.isSelected()){
                    ((Staff<?>) currentUser).removeProductionSystem((String)entitiesComboBox.getSelectedItem());
                    entitiesComboBox.removeItem((String)entitiesComboBox.getSelectedItem());
                }
                Actor actor = null;
                if(addActorRadioButton.isSelected()){
                    actor = new Actor();
                    actor.setname("");
                    ((Staff<?>)currentUser).addActorSystem(actor);
                }
                if(modifyActorRadioButton.isSelected()){
                    actor = crtActor;
                }
                if(addActorRadioButton.isSelected() || modifyActorRadioButton.isSelected()){
                    System.out.println(modifyActorRadioButton.isSelected());
                    actor.setPerformances(performances);
                    actor.setname(actorNameTextField.getText());
                    actor.setbiography(biographyTextArea.getText());

                    JOptionPane.showMessageDialog(new JFrame(), "Successfully added or modified an actor!");
                }
                if(addUserRadioButton.isSelected()){
                    if(mailTextField.getText().isEmpty()
                            || passwordTextField.getText().isEmpty()
                            || nameTextField.getText().isEmpty()
                            || countryTextField.getText().isEmpty()
                            || usernameTextField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                        return;
                    }
                    User<?> user = null;
                    if(userTypeComboBox.getSelectedItem().equals("Admin"))
                        user = UserFactory.factory(AccountType.Admin);
                    if(userTypeComboBox.getSelectedItem().equals("Contributor"))
                        user = UserFactory.factory(AccountType.Contributor);
                    if(userTypeComboBox.getSelectedItem().equals("Regular"))
                        user = UserFactory.factory(AccountType.Regular);

                    Date birthDate = null;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                    try{
                        birthDate = Date.from((LocalDate.parse(birthdateTextField.getText(), formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(new JFrame(), "Wrong date format! Must be yyyy-MM-dd!");
                        return;
                    }
                    for(User<?> user1 : IMDB.getInstance().getUsers())
                        if (user1.getInformation().getCredentials().getEmail().equals(mailTextField.getText())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Mail already taken!");
                            return;
                        }
                    for(User<?> user1 : IMDB.getInstance().getUsers())
                        if (user1.getUsername().equals(nameTextField.getText())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Username already taken!");
                            return;
                        }
                    Credentials credentials = new Credentials(mailTextField.getText(), passwordTextField.getText());

                    User.Information information = new User.Information.InformationBuilder()
                            .credentials(credentials)
                            .name(nameTextField.getText())
                            .country(countryTextField.getText())
                            .age((int)ageSpinner.getValue())
                            .birthDate(birthDate)
                            .gender(genderTextField.getText())
                            .build();

                    user.setUsername(usernameTextField.getText());
                    user.setInformation(information);
                    IMDB.getInstance().getUsers().add(user);
                    JOptionPane.showMessageDialog(new JFrame(), "You have added the user!");
                }
                if(deleteUserRadioButton.isSelected()){
                    User<?> user = IMDB.getInstance().getUser((String)entitiesComboBox.getSelectedItem());
                    IMDB.getInstance().getUsers().remove(user);
                    JOptionPane.showMessageDialog(new JFrame(), "You have removed the user!");
                    entitiesComboBox.removeItem((String)entitiesComboBox.getSelectedItem());
                }
            }
        });
        addSeasonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(seasonNameTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                    return;
                }
                episodeDistribution.put(seasonNameTextField.getText(), new ArrayList<>());
                seasonsComboBox.addItem(seasonNameTextField.getText());
            }
        });
        deleteSeasonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String season = (String)seasonsComboBox.getSelectedItem();
                episodeDistribution.remove(season);
                seasonsComboBox.removeItem(season);
            }
        });
        addEpisodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(episodeDistribution.isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Add a season first!");
                    return;
                }
                if(episodeTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                    return;
                }
                if(epDurationTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                    return;
                }
                Episode episode = new Episode(episodeTextField.getText(), durationTextField.getText());
                episodeDistribution.get((String)seasonsComboBox.getSelectedItem()).add(episode);
                episodesComboBox.addItem(episode.getEpisodeName());
            }
        });
        deleteEpisodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(episodeDistribution.isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Add a season first!");
                    return;
                }
                String season = (String)seasonsComboBox.getSelectedItem();
                Episode episode = getEpisode(season, (String)episodesComboBox.getSelectedItem());
                episodeDistribution.get(season).remove(episode);
                episodesComboBox.removeItem((String)episodesComboBox.getSelectedItem());
            }
        });
        addPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(performanceTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Empty field!");
                    return;
                }
                Actor.Type type =  (Actor.Type)performanceTypeComboBox.getSelectedItem();
                performances.add(new Pair<>(performanceTextField.getText(), type));
                performancesComboBox.addItem(performanceTextField.getText());
            }
        });
        deletePerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Pair<String, Actor.Type> pair = getPair((String)performancesComboBox.getSelectedItem());
                performancesComboBox.removeItem((String)performancesComboBox.getSelectedItem());
                performances.remove(pair);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField2.getText();
                boolean wasFound = false;
                for(Actor actor : IMDB.getInstance().getActors()){
                    if(name.equals(actor.getname()))
                    {
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
                        displayActor(actor, icon);
                        wasFound = true;
                    }
                }
                for(Production production : IMDB.getInstance().getProductions()){
                    if(name.equals(production.title))
                    {
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
                        displayProduction(production, icon);
                        wasFound = true;
                    }
                }
                if(!wasFound){
                    JOptionPane.showMessageDialog(new JFrame(), "No luck, no matches!");
                }
            }
        });
    }
    private Pair<String, Actor.Type> getPair(String name){
        for(Pair<String, Actor.Type> pair : performances){
            if(pair.getTitle().equals(name))
                return pair;
        }
        return null;
    }
    private Episode getEpisode(String season, String name){
        ArrayList<Episode> episodes = (ArrayList<Episode>) episodeDistribution.get(season);
        for(Episode episode : episodes){
            if(episode.getEpisodeName().equals(name))
                return episode;
        }
        return null;
    }
    private void clearActor(){
        performances = new ArrayList<>();
        actorNameTextField.setText("");
        biographyTextArea.setText("");
        entitiesComboBox.setModel(new DefaultComboBoxModel<String>());
        performancesComboBox.setModel(new DefaultComboBoxModel<String>());
        performanceTextField.setText("");
        //performanceTypeComboBox.setModel(new DefaultComboBoxModel());

    }
    private void clearProduction(){
        directorList = new ArrayList<>();
        actorList = new ArrayList<>();
        genreList = new ArrayList<>();
        episodeDistribution = new HashMap<>();

        titleTextField.setText("");
        directorsComboBox.setModel(new DefaultComboBoxModel());
        actorsComboBox.setModel(new DefaultComboBoxModel());
        genresComboBox.setModel(new DefaultComboBoxModel());
        plotTextArea.setText("");
        releaseYearSpinner.setValue(0);
        performancesComboBox.setModel(new DefaultComboBoxModel());
        seasonsComboBox.setModel(new DefaultComboBoxModel());
        seasonNameTextField.setText("");
        episodesComboBox.setModel(new DefaultComboBoxModel());
        episodeTextField.setText("");
        movieRadioButton.setSelected(true);
        durationTextField.setText("");

    }
    private void setCurrentActor(Actor actor){
        crtActor = actor;
        actorNameTextField.setText(actor.name);
        biographyTextArea.setText(actor.getBiography());
        // getting the list of performances
        ArrayList<String> performances = new ArrayList<>();
        for(Pair<String, Actor.Type> performance : actor.getPerformances()){
            performances.add(performance.getTitle());
        }
        performancesComboBox.setModel(new DefaultComboBoxModel(performances.toArray(new String[0])));


    }
    private void setCurrentProduction(Production production){
        crtProduction = production;
        directorList = (ArrayList<String>) production.getDirectors();
        actorList = (ArrayList<String>) production.getActors();
        genreList = (ArrayList<Genre>) production.getGenres();


        titleTextField.setText(production.title);
        directorsComboBox.setModel(new DefaultComboBoxModel(production.getDirectors().toArray(new String[0])));
        actorsComboBox.setModel(new DefaultComboBoxModel(production.getActors().toArray(new String[0])));

        // getting the genres list
        ArrayList<String> genreList = new ArrayList<>();
        for(Genre genre : production.getGenres()){
            genreList.add(genre.toString());
        }

        genresComboBox.setModel(new DefaultComboBoxModel(genreList.toArray(new String[0])));
        plotTextArea.setText(production.getPlot());
        if(production instanceof Movie){
            ((CardLayout)seriesOrMovieHolder.getLayout()).show(seriesOrMovieHolder, "movieCard");

            releaseYearSpinner.setValue(((Movie)production).getReleaseYear());
            ((CardLayout)seriesOrMovieHolder.getLayout()).show(seriesOrMovieHolder, "movieCard");
            durationTextField.setText(((Movie)production).getDuration());
        }
        if(production instanceof Series){
            ((CardLayout)seriesOrMovieHolder.getLayout()).show(seriesOrMovieHolder, "seriesCard");

            releaseYearSpinner.setValue(((Series)production).getReleaseYear());

            // getting the list of seasons
            ArrayList<String> seasons = new ArrayList<>();
            for(String season : ((Series) production).getEpisodeDistribution().keySet()){
                seasons.add(season);
            }
            seasonsComboBox.setModel(new DefaultComboBoxModel(seasons.toArray(new String[0])));
            episodeDistribution = ((Series) production).getEpisodeDistribution();

            ArrayList <Episode> episodes = (ArrayList<Episode>) episodeDistribution.get(seasonsComboBox.getSelectedItem());
            ArrayList <String> episodeNames = new ArrayList<>();
            for(Episode episode : episodes){
                episodeNames.add(episode.getEpisodeName());
            }
            episodesComboBox.setModel(new DefaultComboBoxModel(episodeNames.toArray(new String[0])));
        }
    }

    public void setUserRequests() {
        userRequests = new ArrayList<>();

        for (Request request : IMDB.getInstance().getRequests()) {
            if (request.getUsernameProblem().equals(currentUser.getUsername())) {
                userRequests.add(request);
            }
        }
    }
    void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                setPanelEnabled((JPanel) component, isEnabled);
            }
            component.setEnabled(isEnabled);
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
            currentTabTitle.setText("Admin requests");
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
            currentTabTitle.setText("Requests");

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
            currentTabTitle.setText("Requests");

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
    private ArrayList<String> getUserNameArray(List<User<?>> users){
        ArrayList<String> userNames = new ArrayList<>();
        for(User<?> user :users)
            userNames.add(user.getUsername());
        return userNames;
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
                    displayProduction(production, finalIcon);

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
    private void displayProduction(Production production, ImageIcon finalIcon){
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
                    displayActor(actor, finalIcon);
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
    private void displayActor(Actor actor, ImageIcon finalIcon){
        reviewPanel.setVisible(false);
        ((CardLayout)listHolder.getLayout()).show(listHolder, "productionDetailsCard");

        titleLabel.setText(actor.name);
        description.setText(actor.toString());
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
