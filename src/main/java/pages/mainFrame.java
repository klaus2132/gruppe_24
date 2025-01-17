package pages;
import models.Unit;
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class mainFrame extends JFrame {
    private final Stack<String> pageHistory = new Stack<>();
    private unitPage unitPage;
    private userUnitPage userUnitPage;

    public mainFrame() {
        setTitle("Alian Maid");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel login = new login(this);
        JPanel adminMainPage = new adminMainPage(this);
        JPanel userMainPage = new userMainPage(this);
        unitPage = new unitPage(this);
        JPanel shortcutsPage = new shortcutsPage(this);
        JPanel addUnitPage = new addUnitPage(this);
        userUnitPage = new userUnitPage(this);

        add(login, "Login");
        add(adminMainPage, "Admin Page");
        add(userMainPage, "User Page");
        add(unitPage, "Unit Page");
        add(shortcutsPage, "Shortcut Page");
        add(addUnitPage, "Add Unit Page");
        add(userUnitPage, "User Unit Page");

        showPage("Login");
    }

    public void showEditUnitPage(Unit unit) {
        JPanel editUnitPage = new editUnitPage(this, unit, unitPage);
        add(editUnitPage, "Edit Unit Page");
        showPage("Edit Unit Page");
    }

    //hvis en side og legge til den riktige siden i historien
    public void showPage(String pageName) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        //bare legg til hvis siden ikke er der allerede og hvis historen er tom
        if (pageHistory.isEmpty() || !pageHistory.peek().equals(pageName)) {
            pageHistory.push(pageName);
            System.out.println(pageHistory);
        }
        cl.show(getContentPane(), pageName);
        System.out.println("Navigerte side: " + pageName);
        //oppdaterer tabellen
        if(pageName.equals("Unit Page")){
            unitPage.populateTable();
        }else if(pageName.equals("User Unit Page")){
            userUnitPage.populateTable();
        }
    }

    //navigerer til forrige side
    public void goBack() {
        if (!pageHistory.isEmpty()) {
            //fjerner nåværende side fra history
            pageHistory.pop();
            if (!pageHistory.isEmpty()) {
                //ser på øverste elementet
                String previousPage = pageHistory.peek();
                System.out.println("Navigerte til: " + previousPage);
                CardLayout cl = (CardLayout) getContentPane().getLayout();
                cl.show(getContentPane(), previousPage);
            } else {
                System.out.println("Er ikke mulig å gå lenger bak");
            }
        } else {
            System.out.println("Er ikke mulig å gå lenger bak");
        }
    }
}
