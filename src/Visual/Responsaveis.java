package Visual;

import DAL.ConectaBD;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Responsaveis extends javax.swing.JInternalFrame {

    Connection conecta;
    PreparedStatement pst;
    ResultSet rs;
  
    public Responsaveis() throws ClassNotFoundException {
        initComponents();
        this.setLocation(500,150);
        conecta = ConectaBD.conectabd();
        ListaResponsavel();
    }
    
        public void ExcluirResponsavel(){
        
        String sql = "delete from responsavel where idResponvel=?";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodigo.getText()));
            pst.execute();
            ListaResponsavel(); 
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!");
            
        } catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void SalvarEdicaoResponsavel(){
            String sql = "update responsavel set nomeResponsavel=?, cpfResponsavel=? where idResponvel=? ";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setString(1,txtNome.getText());
            pst.setString(2,txtCPF.getText());
            pst.setInt(3, Integer.parseInt(txtCodigo.getText()));
            pst.executeUpdate();
            txtNome.setEnabled(false);
            txtCPF.setEnabled(false);
            ListaResponsavel();
            JOptionPane.showMessageDialog(null,"Dados atualizado com sucesso!!");
        } catch (SQLException erro ) {
            
            JOptionPane.showMessageDialog(null,erro);
        }
    }
    public void EditarResponsavel(){
        
            txtNome.setEnabled(iconable);
            txtCPF.setEnabled(iconable);
            btnSalvaEdicao.setEnabled(true);
            JOptionPane.showMessageDialog(null,"Apos edição clique no botão salvar edição");

    }
  
    public void LimparTela(){
        txtCodigo.setText("");
        txtNome.setText("");
        txtCPF.setText("");
    }

    public void NovoResponsavel(){
        txtNome.setEnabled(iconable);
        txtCPF.setEnabled(iconable);
        btnSalvar.setEnabled(true);
    }
      
    public void MostrarItens(){
        int seleciona = tblResponsavel.getSelectedRow();
        txtCodigo.setText(tblResponsavel.getModel().getValueAt(seleciona, 0).toString());
        txtNome.setText(tblResponsavel.getModel().getValueAt(seleciona, 1).toString());
        txtCPF.setText(tblResponsavel.getModel().getValueAt(seleciona, 2).toString());
    }
    public void PesquisarResponsavel() {
        
        String sql = "select * from responsavel where nomeResponsavel like ?";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText()+"%");
            rs = pst.executeQuery();
            tblResponsavel.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);

        }
        

    }

    public void ListaResponsavel() {
        String sql = "select * from responsavel order by idResponvel asc";
        try {

            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tblResponsavel.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void CadastrarResponsavel() {
        String sql = "insert into responsavel(nomeResponsavel,cpfResponsavel) values(?,?)";

        try {

            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCPF.getText());
            pst.execute();
            txtNome.setEnabled(false);
            txtCPF.setEnabled(false);
            btnSalvar.setEnabled(false);
            

            JOptionPane.showMessageDialog(null, "Criado com sucesso!!");
            ListaResponsavel();

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, "Já existe um Responsavel com esse CPF");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResponsavel = new javax.swing.JTable();
        btnLimpar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSalvaEdicao = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Responsaveis");

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

        tblResponsavel.setModel(new javax.swing.table.DefaultTableModel(
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
        tblResponsavel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResponsavelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblResponsavel);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setText("Codigo");

        jLabel2.setText("Nome");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel3.setText("CPF");

        btnSalvaEdicao.setText("Salvar Edição");
        btnSalvaEdicao.setEnabled(false);
        btnSalvaEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaEdicaoActionPerformed(evt);
            }
        });

        txtCodigo.setEnabled(false);

        txtNome.setEnabled(false);

        txtCPF.setEnabled(false);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel4.setText("Pesquisar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtCPF))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4)))
                    .addComponent(btnSalvaEdicao))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnNovo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvaEdicao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        EditarResponsavel();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
       ExcluirResponsavel();
       txtNome.setEnabled(false);
       txtCPF.setEnabled(false);
       btnSalvar.setEnabled(false);
       LimparTela();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tblResponsavelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResponsavelMouseClicked
       MostrarItens();
    }//GEN-LAST:event_tblResponsavelMouseClicked

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        LimparTela();
        txtNome.setEnabled(false);
        txtCPF.setEnabled(false);
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        PesquisarResponsavel();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        LimparTela();
        NovoResponsavel();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvaEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaEdicaoActionPerformed
        SalvarEdicaoResponsavel();
        btnSalvaEdicao.setEnabled(false);
    }//GEN-LAST:event_btnSalvaEdicaoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        CadastrarResponsavel();
    }//GEN-LAST:event_btnSalvarActionPerformed


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
    private javax.swing.JTable tblResponsavel;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
