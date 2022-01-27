package View;

public class PrincipalLittlePascal extends javax.swing.JFrame {

        public PrincipalLittlePascal() {
                initComponents();

        }

        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jScrollPane4 = new javax.swing.JScrollPane();
                lexameTable = new javax.swing.JTable();
                jScrollPane2 = new javax.swing.JScrollPane();
                txaOutput = new javax.swing.JTextArea();
                btnClear = new javax.swing.JButton();
                btnCompile = new javax.swing.JButton();
                lblInput = new javax.swing.JLabel();
                lblOutput = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                txaInput = new javax.swing.JTextArea();

                jLabel1.setText("jLabel1");

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Analizador Lexico LittlePascal");

                lexameTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {

                                },
                                new String[] {
                                                "Lexame", "Token", "Line", "Column"
                                }));
                lexameTable.setMaximumSize(new java.awt.Dimension(400, 2000));
                jScrollPane4.setViewportView(lexameTable);
                if (lexameTable.getColumnModel().getColumnCount() > 0) {
                        lexameTable.getColumnModel().getColumn(0).setHeaderValue("Lexame");
                        lexameTable.getColumnModel().getColumn(1).setHeaderValue("Token");
                        lexameTable.getColumnModel().getColumn(2).setHeaderValue("Line");
                        lexameTable.getColumnModel().getColumn(3).setHeaderValue("Column");
                }

                txaOutput.setColumns(30);
                txaOutput.setRows(5);
                txaOutput.setFocusable(false);
                txaOutput.setRequestFocusEnabled(false);
                jScrollPane2.setViewportView(txaOutput);

                btnClear.setBackground(new java.awt.Color(255, 255, 255));
                btnClear.setText("Clear");
                btnClear.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnClearActionPerformed(evt);
                        }
                });

                btnCompile.setBackground(new java.awt.Color(255, 255, 255));
                btnCompile.setText("Compile");
                btnCompile.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCompileActionPerformed(evt);
                        }
                });

                lblInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lblInput.setText("Input");

                lblOutput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                lblOutput.setText("Output");

                txaInput.setColumns(20);
                txaInput.setRows(5);
                txaInput.setMaximumSize(new java.awt.Dimension(600, 2147483647));
                jScrollPane1.setViewportView(txaInput);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(lblOutput)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                .addComponent(jScrollPane2)
                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                309,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(15, 15, 15)))
                                                                .addGap(3, 3, 3)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(btnClear,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                163,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(35, 35, 35)
                                                                                                .addComponent(btnCompile,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(jScrollPane4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                0, Short.MAX_VALUE))
                                                                .addGap(20, 20, 20))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(lblInput)
                                                                .addGap(0, 0, 0)));

                layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
                                new java.awt.Component[] { lblInput, lblOutput });

                layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
                                new java.awt.Component[] { btnClear, btnCompile });

                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(15, 15, 15)
                                                                .addComponent(lblInput)
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                400,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(15, 15, 15)
                                                                                                .addComponent(lblOutput)
                                                                                                .addGap(15, 15, 15)
                                                                                                .addComponent(jScrollPane2))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jScrollPane4,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                478,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGap(45, 45, 45)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btnClear,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                79,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btnCompile,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                79,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(15, 15, 15)));

                pack();
        }// </editor-fold>

        private void btnCompileActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {

        }

        // Variables declaration - do not modify
        private javax.swing.JButton btnClear;
        private javax.swing.JButton btnCompile;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JScrollPane jScrollPane4;
        private javax.swing.JLabel lblInput;
        private javax.swing.JLabel lblOutput;
        private javax.swing.JTable lexameTable;
        private javax.swing.JTextArea txaInput;
        private javax.swing.JTextArea txaOutput;

        public javax.swing.JTextArea getTxaInput() {
                return txaInput;
        }

        public javax.swing.JTextArea getTxaOutput() {
                return txaOutput;
        }

        public javax.swing.JButton getBtnCompile() {
                return btnCompile;
        }

        public javax.swing.JButton getBtnClear() {
                return btnClear;
        }

        public javax.swing.JTable getLexameTable() {
                return lexameTable;
        }
        // End of variables declaration
}
