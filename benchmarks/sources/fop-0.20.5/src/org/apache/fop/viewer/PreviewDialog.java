/*
 * $Id: PreviewDialog.java,v 1.7.2.5 2003/02/25 15:25:15 jeremias Exp $
 * ============================================================================
 *                    The Apache Software License, Version 1.1
 * ============================================================================
 * 
 * Copyright (C) 1999-2003 The Apache Software Foundation. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modifica-
 * tion, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment: "This product includes software
 *    developed by the Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 * 
 * 4. The names "FOP" and "Apache Software Foundation" must not be used to
 *    endorse or promote products derived from this software without prior
 *    written permission. For written permission, please contact
 *    apache@apache.org.
 * 
 * 5. Products derived from this software may not be called "Apache", nor may
 *    "Apache" appear in their name, without prior written permission of the
 *    Apache Software Foundation.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * APACHE SOFTWARE FOUNDATION OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLU-
 * DING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ============================================================================
 * 
 * This software consists of voluntary contributions made by many individuals
 * on behalf of the Apache Software Foundation and was originally created by
 * James Tauber <jtauber@jtauber.com>. For more information on the Apache
 * Software Foundation, please see <http://www.apache.org/>.
 */ 
package org.apache.fop.viewer;

/*
 * originally contributed by
 * Juergen Verwohlt: Juergen.Verwohlt@jCatalog.com,
 * Rainer Steinkuhle: Rainer.Steinkuhle@jCatalog.com,
 * Stanislav Gorkhover: Stanislav.Gorkhover@jCatalog.com
 * Doro Wiarda (wiarda@dwiarda.com):
 * added MessageListener support and made the showing of the progress and error
 * messages Swing thread safe. This is needed as xml parse errors do not
 * necessarily occur in the EventDispatchThread.
 */

import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;

import org.apache.fop.render.awt.*;
import org.apache.fop.messaging.*;
import org.apache.fop.apps.AWTStarter;
import org.apache.fop.apps.FOPException;

/**
 * Frame and User Interface for Preview
 */
public class PreviewDialog extends JFrame implements ProgressListener,
        MessageListener {

    protected Translator res;

    protected int currentPage = 0;
    protected int pageCount = 0;

    protected AWTRenderer renderer;
    protected AWTStarter starter;

    protected IconToolBar toolBar = new IconToolBar();

    protected Command printAction;
    protected Command firstPageAction;
    protected Command previousPageAction;
    protected Command nextPageAction;
    protected Command lastPageAction;
    protected Command reloadAction;
    protected Reloader reloader;

    protected JLabel zoomLabel =
        new JLabel();    // {public float getAlignmentY() { return 0.0f; }};

    protected JComboBox scale = new JComboBox() {
        public float getAlignmentY() {
            return 0.5f;
        }
    };

    protected JScrollPane previewArea = new JScrollPane();
    // protected JLabel statusBar = new JLabel();
    protected JPanel statusBar = new JPanel();
    protected GridBagLayout statusBarLayout = new GridBagLayout();

    protected JLabel statisticsStatus = new JLabel();
    protected JLabel processStatus = new JLabel();
    protected JLabel infoStatus = new JLabel();
    protected JLabel previewImageLabel = new JLabel();

     /**
     * Create a new PreviewDialog that uses the given starter, renderer and translator.
     *
     *  @param aStarter the to use starter
     *  @param aRenderer the to use renderer
     *  @param aRes the to use translator
     */
    public PreviewDialog(AWTStarter aStarter, AWTRenderer aRenderer, Translator aRes) {
        this(aRenderer, aRes);
        starter = aStarter;
    }

    /**
     * Create a new PreviewDialog that uses the given renderer and translator.
     *
     * @param aRenderer the to use renderer
     * @param aRes the to use translator
     */
    public PreviewDialog(AWTRenderer aRenderer, Translator aRes) {
        res = aRes;
        renderer = aRenderer;

        printAction = new Command(res.getString("Print"), "Print") {
            public void doit() {
                print();
            }
        };

        firstPageAction = new Command(res.getString("First page"),
                                      "firstpg") {
            public void doit() {
                goToFirstPage(null);
            }

        };

        previousPageAction = new Command(res.getString("Previous page"),
                                         "prevpg") {
            public void doit() {
                goToPreviousPage(null);
            }
        };

        nextPageAction = new Command(res.getString("Next page"), "nextpg") {
            public void doit() {
                goToNextPage(null);
            }
        };

        lastPageAction = new Command(res.getString("Last page"), "lastpg") {
            public void doit() {
                goToLastPage(null);
            }
        };

        reloadAction = new Command(res.getString("Reload"), "reload") {
            public void doit() {
                reload(null);
            }
        };

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(379, 476));
        previewArea.setMinimumSize(new Dimension(50, 50));

        this.setTitle("FOP: AWT-" + res.getString("Preview"));

        scale.addItem("25");
        scale.addItem("50");
        scale.addItem("75");
        scale.addItem("100");
        scale.addItem("150");
        scale.addItem("200");

        scale.setMaximumSize(new Dimension(80, 24));
        scale.setPreferredSize(new Dimension(80, 24));

        scale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scale_actionPerformed(e);
            }
        });

        scale.setSelectedItem("100");
        renderer.setScaleFactor(100.0);

        zoomLabel.setText(res.getString("Zoom"));

        this.setJMenuBar(setupMenue());

        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        toolBar.add(printAction);
        toolBar.add(reloadAction);
        toolBar.addSeparator();
        toolBar.add(firstPageAction);
        toolBar.add(previousPageAction);
        toolBar.add(nextPageAction);
        toolBar.add(lastPageAction);
        toolBar.addSeparator();
        toolBar.add(zoomLabel, null);
        toolBar.addSeparator();
        toolBar.add(scale, null);

        this.getContentPane().add(previewArea, BorderLayout.CENTER);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);

        statisticsStatus.setBorder(BorderFactory.createEtchedBorder());
        processStatus.setBorder(BorderFactory.createEtchedBorder());
        infoStatus.setBorder(BorderFactory.createEtchedBorder());

        statusBar.setLayout(statusBarLayout);

        processStatus.setPreferredSize(new Dimension(200, 21));
        statisticsStatus.setPreferredSize(new Dimension(100, 21));
        infoStatus.setPreferredSize(new Dimension(100, 21));
        processStatus.setMinimumSize(new Dimension(200, 21));
        statisticsStatus.setMinimumSize(new Dimension(100, 21));
        infoStatus.setMinimumSize(new Dimension(100, 21));
        statusBar.add(processStatus,
                      new GridBagConstraints(0, 0, 2, 1, 2.0, 0.0,
                                             GridBagConstraints.CENTER,
                                             GridBagConstraints.HORIZONTAL,
                                             new Insets(0, 0, 0, 5), 0, 0));
        statusBar.add(statisticsStatus,
                      new GridBagConstraints(2, 0, 1, 2, 1.0, 0.0,
                                             GridBagConstraints.CENTER,
                                             GridBagConstraints.HORIZONTAL,
                                             new Insets(0, 0, 0, 5), 0, 0));
        statusBar.add(infoStatus,
                      new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
                                             GridBagConstraints.CENTER,
                                             GridBagConstraints.HORIZONTAL,
                                             new Insets(0, 0, 0, 0), 0, 0));

        previewArea.getViewport().add(previewImageLabel);
        showPage();
    }

    /**
     * Create a new menubar to be shown in this window.
     *
     * @return the newly created menubar
     */
    private JMenuBar setupMenue() {
        JMenuBar menuBar;
        JMenuItem menuItem;
        JMenu menu;
        JMenu subMenu;

        menuBar = new JMenuBar();
        menu = new JMenu(res.getString("File"));
        subMenu = new JMenu("OutputFormat");
        subMenu.add(new Command("mHTML"));
        subMenu.add(new Command("mPDF"));
        subMenu.add(new Command("mRTF"));
        subMenu.add(new Command("mTEXT"));
        // menu.add(subMenu);
        // menu.addSeparator();
        menu.add(new Command(res.getString("Print")) {
            public void doit() {
                print();
            }
        });
        menu.add(new Command(res.getString("Reload")) {
            public void doit() {
                reload(null);
            }
        });
        menu.addSeparator();
        menu.add(new Command(res.getString("Exit")) {
            public void doit() {
                dispose();
            }
        });
        menuBar.add(menu);
        menu = new JMenu(res.getString("View"));
        menu.add(new Command(res.getString("First page")) {
            public void doit() {
                goToFirstPage(null);
            }
        });
        menu.add(new Command(res.getString("Previous page")) {
            public void doit() {
                goToPreviousPage(null);
            }
        });
        menu.add(new Command(res.getString("Next page")) {
            public void doit() {
                goToNextPage(null);
            }
        });
        menu.add(new Command(res.getString("Last page")) {
            public void doit() {
                goToLastPage(null);
            }
        });
        menu.add(new Command(res.getString("Go to Page") + " ...") {
            public void doit() {
                goToPage(null);
            }
        });
        menu.addSeparator();
        subMenu = new JMenu(res.getString("Zoom"));
        subMenu.add(new Command("25%") {
            public void doit() {
                setScale(25.0);
            }
        });
        subMenu.add(new Command("50%") {
            public void doit() {
                setScale(50.0);
            }
        });
        subMenu.add(new Command("75%") {
            public void doit() {
                setScale(75.0);
            }
        });
        subMenu.add(new Command("100%") {
            public void doit() {
                setScale(100.0);
            }
        });
        subMenu.add(new Command("150%") {
            public void doit() {
                setScale(150.0);
            }
        });
        subMenu.add(new Command("200%") {
            public void doit() {
                setScale(200.0);
            }
        });
        menu.add(subMenu);
        menu.addSeparator();
        menu.add(new Command(res.getString("Default zoom")) {
            public void doit() {
                setScale(100.0);
            }
        });
        menuBar.add(menu);
        menu = new JMenu(res.getString("Help"));
        menu.add(new Command(res.getString("Index")));
        menu.addSeparator();
        menu.add(new Command(res.getString("Introduction")));
        menu.addSeparator();
        menu.add(new Command(res.getString("About")) {
            public void doit() {
                startHelpAbout(null);
            }
        });
        menuBar.add(menu);
        return menuBar;
    }

    /**
     * Show the About box
     *
     * @param e a value of type 'ActionEvent'
     */
    public void startHelpAbout(ActionEvent e) {
        PreviewDialogAboutBox dlg = new PreviewDialogAboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.show();
    }

    /**
     * Change the current visible page
     *
     * @param number the page number to go to
     */
    private void goToPage(int number) {
        currentPage = number;
        renderer.setPageNumber(number);
        showPage();
    }

    /**
     * Shows the previous page.
     */
    private void goToPreviousPage(ActionEvent e) {
        if (currentPage <= 0)
            return;
        currentPage--;
        goToPage(currentPage);
    }

    /**
     * Shows the next page.
     */
    private void goToNextPage(ActionEvent e) {
        if (currentPage >= pageCount - 1)
            return;
        currentPage++;
        goToPage(currentPage);
    }

    /**
     * Shows the last page.
     */
    private void goToLastPage(ActionEvent e) {
        if (currentPage == pageCount - 1)
            return;
        currentPage = pageCount - 1;
        goToPage(currentPage);
    }

    /**
     * Reloads and reformats document.
     */
    private synchronized void reload(ActionEvent e) {
        if (reloader == null || !reloader.isAlive()) {
            reloader = new Reloader();
            reloader.start();
        }
    }

    /**
     * This class is used to reload document  in
     * a thread safe way.
     */
    private class Reloader extends Thread {
        public void run() {
            previewImageLabel.setIcon(null);
            statisticsStatus.setText("");
            //Cleans up renderer
            while (renderer.getPageCount() != 0)
                renderer.removePage(0);
            try {
                starter.run();
            } catch (FOPException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shows a page by number.
     */
    private void goToPage(ActionEvent e) {
        GoToPageDialog d = new GoToPageDialog(this,
                                              res.getString("Go to Page"),
                                              true,
                                              res);
        d.setLocation((int)getLocation().getX() + 50,
                      (int)getLocation().getY() + 50);
        d.show();
        currentPage = d.getPageNumber();

        if (currentPage < 1 || currentPage > pageCount)
            return;

        currentPage--;

        goToPage(currentPage);
    }

    /**
     * Shows the first page.
     */
    private void goToFirstPage(ActionEvent e) {
        if (currentPage == 0)
            return;
        currentPage = 0;
        goToPage(currentPage);
    }

    private void print() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        // Not necessary, Pageable gets a Printable.
        // pj.setPrintable(renderer);
        pj.setPageable(renderer);

        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (PrinterException pe) {
                pe.printStackTrace();
            }
        }
    }

    public void setScale(double scaleFactor) {
        if (scaleFactor == 25.0)
            scale.setSelectedIndex(0);
        else if (scaleFactor == 50.0)
            scale.setSelectedIndex(1);
        else if (scaleFactor == 75.0)
            scale.setSelectedIndex(2);
        else if (scaleFactor == 100.0)
            scale.setSelectedIndex(3);
        else if (scaleFactor == 150.0)
            scale.setSelectedIndex(4);
        else if (scaleFactor == 200.0)
            scale.setSelectedIndex(5);

        renderer.setScaleFactor(scaleFactor);
        showPage();
    }

    void scale_actionPerformed(ActionEvent e) {
        setScale(new Double((String)scale.getSelectedItem()).doubleValue());
    }

    public void progress(int percentage) {
        progress(new String(percentage + "%"));
    }

    public void progress(int percentage, String message) {
        progress(new String(message + " " + percentage + "%"));
    }

    /**
     * Setting the text  of a JLabel is not thread save, it
     * needs to be done in  the EventThread. Here we make sure
     * it is done.
     */
    public void progress(String message) {
        SwingUtilities.invokeLater(new showProgress(message, false));
    }

    /**
     * This class is used to show status and error messages in
     * a thread safe way.
     */
    class showProgress implements Runnable {
        /**
         * The message to display
         */
        Object message;

        /**
         * Is this an errorMessage, i.e. should it be shown in
         * an JOptionPane or in the status bar.
         */
        boolean isErrorMessage = false;

        /**
         * Constructs  showProgress thread
         * @param message message to display
         * @param isErrorMessage show in status bar or in JOptionPane
         */
        public showProgress(Object message, boolean isErrorMessage) {
            this.message = message;
            this.isErrorMessage = isErrorMessage;
        }

        public void run() {
            if (isErrorMessage) {
                JOptionPane.showMessageDialog(null, message, "Error",
                                              JOptionPane.ERROR_MESSAGE);
            } else
                processStatus.setText(message.toString());
        }
    }

    public void showPage() {
        showPageImage viewer = new showPageImage();

        if (SwingUtilities.isEventDispatchThread()) {
            viewer.run();
        } else
            SwingUtilities.invokeLater(viewer);
    }

    /**
     * This class is used to update the page image
     * in a thread safe way.
     */
    class showPageImage implements Runnable {

        /**
         * The run method that does the actuall updating
         */

        public void run() {
            BufferedImage pageImage = null;
            Graphics graphics = null;

            renderer.render(currentPage);
            pageImage = renderer.getLastRenderedPage();
            if (pageImage == null)
                return;
            graphics = pageImage.getGraphics();
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, pageImage.getWidth() - 1,
                              pageImage.getHeight() - 1);

            previewImageLabel.setIcon(new ImageIcon(pageImage));

            pageCount = renderer.getPageCount();

            statisticsStatus.setText(res.getString("Page") + " "
                                     + (currentPage + 1) + " "
                                     + res.getString("of") + " " + pageCount);
        }
    }

    /**
     * Called by MessageHandler if an error message or a
     * log message is received.
     */
    public void processMessage(MessageEvent event) {
        String error = event.getMessage();
        String text = processStatus.getText();
        FontMetrics fmt =
            processStatus.getFontMetrics(processStatus.getFont());
        int width = processStatus.getWidth() - fmt.stringWidth("...");
        showProgress showIt;

        if (event.getMessageType() == event.LOG) {
            if (!text.endsWith("\n")) {
                text = text + error;
                while (fmt.stringWidth(text) > width) {
                    text = text.substring(1);
                    width = processStatus.getWidth() - fmt.stringWidth("...");
                }
            } else
                text = error;
            progress(text);
        } else {
            error = error.trim();
            if (error.equals(">")) {
                text = text + error;
                while (fmt.stringWidth(text) > width) {
                    text = text.substring(1);
                    width = processStatus.getWidth() - fmt.stringWidth("...");
                }
                progress(processStatus.getText() + error);
                return;
            }
            if (error.equals(""))
                return;
            if (error.length() < 60) {
                showIt = new showProgress(error, true);
            } else {
                StringTokenizer tok = new StringTokenizer(error, " ");
                Vector labels = new Vector();
                StringBuffer buffer = new StringBuffer();
                String tmp, list[];

                while (tok.hasMoreTokens()) {
                    tmp = tok.nextToken();
                    if ((buffer.length() + tmp.length() + 1) < 60) {
                        buffer.append(" ").append(tmp);
                    } else {
                        labels.add(buffer.toString());
                        buffer = new StringBuffer();
                        buffer.append(tmp);
                    }
                }
                labels.add(buffer.toString());
                list = new String[labels.size()];
                for (int i = 0; i < labels.size(); i++) {
                    list[i] = labels.get(i).toString();
                }
                showIt = new showProgress(list, true);
            }
            if (SwingUtilities.isEventDispatchThread()) {
                showIt.run();
            } else {
                try {
                    SwingUtilities.invokeAndWait(showIt);
                } catch (Exception e) {
                    e.printStackTrace();
                    progress(event.getMessage());
                }
            }
        }
    }

    public void reportException(Exception e) {
        String msg = res.getString("An exception has occured");
        progress(msg);
        JOptionPane.showMessageDialog(
            getContentPane(),
            "<html><b>" + msg + ":</b><br>"
             + e.getClass().getName() + "<br>" + e.getMessage() + "</html>", res.getString("Fatal error"),
             JOptionPane.ERROR_MESSAGE
        );
    }

}    // class PreviewDialog
