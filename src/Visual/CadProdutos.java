
package Visual;
import DAL.ConectaBD;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class CadProdutos extends javax.swing.JInternalFrame {

    Connection conecta;
    PreparedStatement pst;
    ResultSet rs;

    public CadProdutos() throws ClassNotFoundException {
        initComponents();
        this.setLocation(500,150);
        conecta = ConectaBD.conectabd();
        ListaProduto();
    }
    
        public void ExcluirProduto(){
        
        String sql = "delete from produto where idProduto=?";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodigo.getText()));
            pst.execute();
            ListaProduto(); 
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!");
            
        } catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void SalvarEdicaoProduto(){
            String sql = "update produto set nomeProduto=?, FabricanteProduto=? where idProduto=? ";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setString(1,txtNomeProduto.getText());
            pst.setString(2,txtFabricanteProduto.getText());
            pst.setInt(3, Integer.parseInt(txtCodigo.getText()));
            pst.executeUpdate();
            txtNomeProduto.setEnabled(false);
            txtFabricanteProduto.setEnabled(false);
            ListaProduto();
            JOptionPane.showMessageDialog(null,"Dados atualizado com sucesso!!");
        } catch (SQLException erro ) {
            
            JOptionPane.showMessageDialog(null,erro);
        }
    }
    public void EditarProduto(){
        
            txtNomeProduto.setEnabled(iconable);
            txtFabricanteProduto.setEnabled(iconable);
            btnSalvaEdicao.setEnabled(true);
            JOptionPane.showMessageDialog(null,"Apos edição clique no botão salvar edição");

    }
    public void LimparTela(){
        txtCodigo.setText("");
        txtNomeProduto.setText("");
        txtFabricanteProduto.setText("");
    }
    public void NovoProduto(){
        txtNomeProduto.setEnabled(iconable);
        txtFabricanteProduto.setEnabled(iconable);
        btnSalvar.setEnabled(true);
    }
      
    public void MostrarItens(){
        int seleciona = tblProduto.getSelectedRow();
        txtCodigo.setText(tblProduto.getModel().getValueAt(seleciona, 0).toString());
        txtNomeProduto.setText(tblProduto.getModel().getValueAt(seleciona, 1).toString());
        txtFabricanteProduto.setText(tblProduto.getModel().getValueAt(seleciona, 2).toString());
    }
    public void PesquisarProduto() {
        
        String sql = "select * from produto where nomeProduto like ?";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText()+"%");
            rs = pst.executeQuery();
            tblProduto.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);

        }
        

    }

    public void ListaProduto() {
        String sql = "select * from produto order by idProduto asc";
        try {

            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tblProduto.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void CadastrarProduto() {
        String sql = "insert into produto(nomeProduto,FabricanteProduto) values(?,?)";

        try {

            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtNomeProduto.getText());
            pst.setString(2, txtFabricanteProduto.getText());
            pst.execute();
            txtNomeProduto.setEnabled(false);
            txtFabricanteProduto.setEnabled(false);
            btnSalvar.setEnabled(false);
            

            JOptionPane.showMessageDialog(null, "Criado com sucesso!!");
            ListaProduto();

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, error);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        txtFabricanteProduto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        btnSalvaEdicao = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Produtos");

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar");

        jLabel1.setText("Codigo");

        txtCodigo.setEnabled(false);

        jLabel2.setText("Nome");

        txtNomeProduto.setEnabled(false);

        txtFabricanteProduto.setEnabled(false);

        jLabel3.setText("Fabricante");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        btnSalvaEdicao.setText("Salvar Edição");
        btnSalvaEdicao.setEnabled(false);
        btnSalvaEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaEdicaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFabricanteProduto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(btnSalvaEdicao))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtFabricanteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvaEdicao)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        PesquisarProduto();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        LimparTela();
        NovoProduto();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        CadastrarProduto();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        EditarProduto();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        ExcluirProduto();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        LimparTela();
        txtNomeProduto.setEnabled(false);
        txtFabricanteProduto.setEnabled(false);
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        MostrarItens();
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void btnSalvaEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaEdicaoActionPerformed
      
          SalvarEdicaoProduto();
          LimparTela();
    }//GEN-LAST:event_btnSalvaEdicaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvaEdicao;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFabricanteProduto;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
