/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mangamanagetool;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.util.Arrays;

/**
 *
 * @author jhuang
 */
public class MainInterface extends javax.swing.JFrame
{
    private final String UNSORTED_FOLDER_PATH_PROPERTY = "unsorted folder path";
    private final String SORTED_FOLDER_PATH_PROPERTY = "sort folder path";
    private final String SETTING_FILE = "property.xml";

    /**
     * Creates new form MainInterface
     */
    public MainInterface()
    {
        initComponents();
        loadUserSetting();
    }

    /*
     * load User Setting into UI
     */
    private void loadUserSetting()
    {
        try
        {
            FileInputStream in = new FileInputStream(SETTING_FILE);
            Properties userSetting = new Properties();
            userSetting.loadFromXML(in);

            //xml parser
            XStream xstream = new XStream(new DomDriver());

            if (userSetting.getProperty(UNSORTED_FOLDER_PATH_PROPERTY) != null)
            {
                String[] unsortedPathArr = (String[]) xstream.fromXML(userSetting.getProperty(UNSORTED_FOLDER_PATH_PROPERTY));
                for (String s : unsortedPathArr)
                {
                     if (s!=null)
                    {
                        getDefaultListModel(unsortedFolderList).addElement(s);
                    }
                }
            }

            if (userSetting.getProperty(SORTED_FOLDER_PATH_PROPERTY) != null)
            {
                String[] sortedPathArr = (String[]) xstream.fromXML(userSetting.getProperty(SORTED_FOLDER_PATH_PROPERTY));
                for (String s : sortedPathArr)
                {
                    if (s!=null)
                    {
                         getDefaultListModel(sortedFolderList).addElement(s);
                    }
                }
            }

            in.close();
        }
        catch (Exception e)
        {
            System.err.println("Fail to load user setting");
        }
    }

    /**
     *  save User Setting from UI
     */
    private void saveUserSetting()
    {
        try
        {
            String unsortedPath = "";
            String sortedPath = "";
            Properties userSetting = null;
            
            if (this.rememberBox.isSelected())
            {
                Object[] tempUnsortedArr = getDefaultListModel(unsortedFolderList).toArray();
                String[] unsortedPathArr = Arrays.copyOf(tempUnsortedArr, tempUnsortedArr.length, String[].class);
                
         
                Object[] tempSortedArr = getDefaultListModel(sortedFolderList).toArray();
                String[] sortedPathArr = Arrays.copyOf(tempSortedArr, tempSortedArr.length, String[].class);

                //init xml generator
                XStream xstream = new XStream(new StaxDriver());


                unsortedPath = xstream.toXML(unsortedPathArr);
                sortedPath = xstream.toXML(sortedPathArr);
            }

            userSetting = new Properties();
            userSetting.setProperty(UNSORTED_FOLDER_PATH_PROPERTY, unsortedPath);
            userSetting.setProperty(SORTED_FOLDER_PATH_PROPERTY, sortedPath);

            FileOutputStream out = new FileOutputStream(SETTING_FILE);
            userSetting.storeToXML(out, "Setting of user's folders");

            out.close();
        }
        catch (Exception e)
        {

            System.err.println("Fail to save user setting" + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        runButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        rememberBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        unsortedFolderList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        sortedFolderList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Manga Sorter"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                run(evt);
            }
        });

        jButton1.setText("Add Folders Where Unsorted Files Are (Required)");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                chooseWhereUnsortedFilesAre(evt);
            }
        });

        jButton2.setText("Add Folders Where Sorted Files Are (Opitional)");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ChooseWhereSortedFIlesAre(evt);
            }
        });

        rememberBox.setSelected(true);
        rememberBox.setText("Remember Folders");

        unsortedFolderList.setModel(new DefaultListModel());
        unsortedFolderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        unsortedFolderList.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                unsortedFolderListKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(unsortedFolderList);

        sortedFolderList.setModel(new DefaultListModel());
        sortedFolderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sortedFolderList.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                sortedFolderListKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(sortedFolderList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rememberBox)
                        .addGap(18, 18, 18)
                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane2)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rememberBox))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * a method for file choosing
     */
    private  File lastTimeChoice = null;   //a function-only local vairable for chooseFile
    private String chooseFile()
    {
        //Create a file chooser
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setApproveButtonText("choose");
        
        chooser.setCurrentDirectory(lastTimeChoice);
        
        //In response to a button click:
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            //System.out.println("You chose to open this file: " + chooser.getSelectedFile());
            lastTimeChoice = chooser.getSelectedFile().getParentFile();
            return chooser.getSelectedFile().getPath();
        }
        else
        {
            return null;
        }
    }

    /*
     * the most important method
     * read folder and then do the matching
     */
    private void run(java.awt.event.ActionEvent evt)//GEN-FIRST:event_run
    {//GEN-HEADEREND:event_run

        this.runButton.setEnabled(false);
        //this.runButton.setText("Running...");


        UnsortedFileTable unsortedFileList = new UnsortedFileTable();
        SortedFileTable authorList = new SortedFileTable();

        try
        {

            Object[] tempUnsortedArr = getDefaultListModel(unsortedFolderList).toArray();
             String[] unsortedPathArr = Arrays.copyOf(tempUnsortedArr, tempUnsortedArr.length, String[].class);

            for (String path : unsortedPathArr)
            {
                 unsortedFileList.addFolder(path.trim());
            }

            unsortedFileList.debugDisplay();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getStackTrace() + " during scanning unsorted files");
            JOptionPane.showMessageDialog(this, e + " during scanning unsorted files");
            this.runButton.setEnabled(true);
            //this.runButton.setText("Run");
            return;
        }


        try
        {
            Object[] tempSortedArr = getDefaultListModel(sortedFolderList).toArray();
            String[] sortedPathArr = Arrays.copyOf(tempSortedArr, tempSortedArr.length, String[].class);

            for (String path : sortedPathArr)
            {
                authorList.addFolder(path.trim());
            }

            authorList.debugDisplay();
            
        }
        catch (Exception e)
        {
            System.err.println(e + "during scanning Sorted folder");
            JOptionPane.showMessageDialog(this, e + "during scanning Sorted folder");
            this.runButton.setEnabled(true);
            //this.runButton.setText("Run");
            return;
        }

        try
        {
            moveFileAndMkdir(authorList, unsortedFileList);
            // JOptionPane.showMessageDialog(this, "Please check the project. Command files are created successfully");
        }
        catch (Exception e)
        {
            System.err.println(e + "during calculation");
            JOptionPane.showMessageDialog(this, e + "during calculation");
        }
        finally
        {
            this.runButton.setEnabled(true);
            //this.runButton.setText("Run");
        }
    }//GEN-LAST:event_run

     /*
     * choose folder for unsorted list
     */
    private void chooseWhereUnsortedFilesAre(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseWhereUnsortedFilesAre
    {//GEN-HEADEREND:event_chooseWhereUnsortedFilesAre
        String file = chooseFile();
        getDefaultListModel(unsortedFolderList).addElement(file);
    }//GEN-LAST:event_chooseWhereUnsortedFilesAre

    /*
     * choose folder for sorted list
     */
    private void ChooseWhereSortedFIlesAre(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseWhereSortedFIlesAre
        String file = chooseFile();
        getDefaultListModel(sortedFolderList).addElement(file);
    }//GEN-LAST:event_ChooseWhereSortedFIlesAre

    /*
     * called when program closing
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.saveUserSetting();
    }//GEN-LAST:event_formWindowClosing

    /*
     *  delete list item from unsortedFolderList
     */
    private void unsortedFolderListKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_unsortedFolderListKeyReleased
    {//GEN-HEADEREND:event_unsortedFolderListKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            int index = unsortedFolderList.getSelectedIndex();
            getDefaultListModel(unsortedFolderList).removeElementAt(index);
        }
    }//GEN-LAST:event_unsortedFolderListKeyReleased

    /*
     * delete list item from sortedFolderList
     */
    private void sortedFolderListKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_sortedFolderListKeyReleased
    {//GEN-HEADEREND:event_sortedFolderListKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            int index = sortedFolderList.getSelectedIndex();
            getDefaultListModel(sortedFolderList).removeElementAt(index);
        }
    }//GEN-LAST:event_sortedFolderListKeyReleased

    /**
     * a simple utility to get the defaultListModel of JList
     *
     * @param list
     * @return DefaultListModel
     */
    private DefaultListModel getDefaultListModel(JList list)
    {
        return (DefaultListModel) list.getModel();
    }
    


    /**
     * find matched files between SortedFileTable and UnsortedFileTable print
     * out instruction about how to move file from unsortedFolder to
     * sortedFolder You need to move file manually to make sure no any problem
     *
     * @param existingAuthorList
     * @param unsortedFileList
     * @throws Exception
     */
    private void moveFileAndMkdir(SortedFileTable existingAuthorList, UnsortedFileTable unsortedFileList) throws Exception
    {

        //get the author of unsorted files     
        ArrayList<String> unsortedAuthorNames = new ArrayList(unsortedFileList.table.keySet());
        //sort them for more readable output
        Collections.sort(unsortedAuthorNames);

        int mvCounter = 0;
        int mkdirCounter = 0;

        //command to make new folder 
        //windows and mac both use "mkdir" to make new directory
        StringBuilder mkdrirBuilder = new StringBuilder();

        //command to move files one windows
        StringBuilder mvWinCommand = new StringBuilder();

        //command to move file on mac and linux
        StringBuilder mvMacCommand = new StringBuilder();

        StringBuilder mkdirCommand = new StringBuilder();

        //a non-shell version for human to read
        StringBuilder mvStr = new StringBuilder();
        StringBuilder mkdirStr = new StringBuilder();

        for (String unsortedAuthorName : unsortedAuthorNames)
        {
            //check if there already exist a folder for the file
            File destFolder = getAuthorFolder(existingAuthorList, unsortedAuthorName, unsortedFileList.table.get(unsortedAuthorName).names);

            if (destFolder != null)
            {
                //tell user to move the file
                ArrayList<File> sourceFiles = unsortedFileList.table.get(unsortedAuthorName).files;
                for (File sourceFile : sourceFiles)
                {
                    mvMacCommand.append("mv \"").append(sourceFile.getPath()).append("\" \"").append(destFolder.getPath()).append("\"\n\r");
                    mvWinCommand.append("move \"").append(sourceFile.getPath()).append("\" \"").append(destFolder.getPath()).append("\"\n\r");

                    mvStr.append(sourceFile.getName()).append("    ").append(destFolder.getName()).append("\n\r");

                    mvCounter++;
                }

                //move src dest
            }
            //if folder  does not exist and this author have more than two book
            //tell user to create one
            else if (unsortedFileList.table.get(unsortedAuthorName).files.size() > 3)
            {

                mkdirCommand.append("mkdir \"").append(unsortedAuthorName).append("\"\n\r");
                mkdirStr.append(unsortedAuthorName).append("\n\r");

                mkdirCounter++;
            }
        }

        //write result into text files
        PrintWriter out;

        String saveFolder = System.getProperty("user.dir");

        out = new PrintWriter(saveFolder + "\\" + "mv_win_command.txt");
        mvWinCommand.insert(0, "Need to move files " + mvCounter + " times\n\r\n\r");
        out.print(mvWinCommand.toString());
        out.close();

        out = new PrintWriter(saveFolder + "\\" + "mv_mac_command.txt");
        mvMacCommand.insert(0, "Need to move files " + mvCounter + " times\n\r\n\r");
        out.print(mvMacCommand.toString());
        out.close();

        out = new PrintWriter(saveFolder + "\\" + "mkdir_command.txt");
        mkdirCommand.insert(0, "Need to create " + mkdirCounter + " new folders\n\r\n\r");
        out.print(mkdirCommand.toString());
        out.close();

        out = new PrintWriter(saveFolder + "\\" + "mkdir_easy_read.txt");
        mkdirStr.insert(0, "Need to create " + mkdirCounter + " new folders\n\r\n\r");
        out.print(mkdirStr.toString());
        out.close();

        out = new PrintWriter(saveFolder + "\\" + "mv_easy_read.txt");
        mvStr.insert(0, "Need to move files " + mvCounter + " times\n\r\n\r");
        out.print(mvStr.toString());
        out.close();

        System.out.println(mvWinCommand);

        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.print(mkdrirBuilder);
        System.out.println(mkdirCommand);

        java.awt.Desktop.getDesktop().open(new File(saveFolder));
    }

    //@param s: author name
    //@return the folder url, null if no exitence
    public File getAuthorFolder(SortedFileTable sortedtable, String sourceName, ArrayList<String> sourceNames)
    {
        //if we can find directly, nice
        if (sortedtable.table.contains(sourceName))
        {
            return sortedtable.table.get(sourceName).directory;
        }

        //if not, compare all authors names
        for (AuthorInfo entry : sortedtable.table.values())
        {
            for (String name : entry.names)
            {
                for (String name2 : sourceNames)
                {

                    // System.out.println(name2 + "  "+ name);
                    int strDistance = NameParser.stringDistance(name2, name);

                    if (strDistance == 0)
                    {
                        return entry.directory;
                    }
                    else if (strDistance == 1 && name2.length() > 2 && name.length() > 2)
                    {
                        return entry.directory;
                    }
                }
            }

        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainInterface().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox rememberBox;
    private javax.swing.JButton runButton;
    private javax.swing.JList sortedFolderList;
    private javax.swing.JList unsortedFolderList;
    // End of variables declaration//GEN-END:variables
}
