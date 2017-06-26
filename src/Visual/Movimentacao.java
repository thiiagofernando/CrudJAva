package Visual;
import DAL.ConectaBD;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Movimentacao extends javax.swing.JInternalFrame {

    Connection conecta;
    PreparedStatement pst;
    ResultSet rs;
   
    public Movimentacao() throws ClassNotFoundException {
        initComponents();
        this.setLocation(500,100);
        conecta = ConectaBD.conectabd();
        ListaMovimentacao();
        this.PopularComboProduto();
        this.PopularComboResponsavel();
    }
    
    public void PopularComboResponsavel(){
        String sql = "select nomeResponsavel from responsavel";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ComboResponsavel.addItem(rs.getString("nomeResponsavel"));
            }
            
        } 
        catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void PopularComboProduto(){
        String sql = "select nomeProduto from produto";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ComboProduto.addItem(rs.getString("nomeProduto"));
            }
            
        } 
        catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void ExcluirMovimentacao(){
        
        String sql = "delete from movimentacao where idMovimentacao=?";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodigo.getText()));
            pst.execute();
            ListaMovimentacao(); 
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!");
            
        } catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void SalvarEdicaoMovimentacao(){
            String sql = "update movimentacao set TipoMovimentacao=?, Produto=?, Responsavel=?  where idMovimentacao=? ";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setString(1, (String) ComboTipo.getSelectedItem());
            pst.setString(2, (String) ComboProduto.getSelectedItem());
            pst.setString(3, (String) ComboResponsavel.getSelectedItem());
            pst.setInt(4, Integer.parseInt(txtCodigo.getText()));
            pst.executeUpdate();
            ComboTipo.setEnabled(false);
            ComboProduto.setEnabled(false);
            ComboResponsavel.setEnabled(false);
            ListaMovimentacao();
            JOptionPane.showMessageDialog(null,"Dados atualizado com sucesso!!");
        } catch (SQLException erro ) {
            
            JOptionPane.showMessageDialog(null,erro);
        }
    }
    public void EditarMovimentacao(){
        
            ComboTipo.setEnabled(iconable);
            ComboProduto.setEnabled(iconable);
            ComboResponsavel.setEnabled(iconable);
            btnSalvaEdicao.setEnabled(true);
            JOptionPane.showMessageDialog(null,"Apos edição clique no botão salvar edição");

    }
    public void LimparTela(){
        ComboTipo.getSelectedItem();
        ComboProduto.getSelectedItem();
        ComboResponsavel.getSelectedItem();
    }
    public void NovoMovimentacao(){
        ComboTipo.setEnabled(iconable);
        ComboProduto.setEnabled(iconable);
        ComboResponsavel.setEnabled(iconable);
        btnSalvar.setEnabled(true);
    }
      
    public void MostrarItens(){
        int seleciona = tblMovimentacao.getSelectedRow();
        txtCodigo.setText(tblMovimentacao.getModel().getValueAt(seleciona, 0).toString());
        ComboTipo.setSelectedItem(tblMovimentacao.getModel().getValueAt(seleciona, 1).toString());
        ComboProduto.setSelectedItem(tblMovimentacao.getModel().getValueAt(seleciona, 2).toString());
        ComboResponsavel.setSelectedItem(tblMovimentacao.getModel().getValueAt(seleciona, 3).toString());
    }
    public void PesquisarMovimentacao() {
        
        String sql = "select * from movimentacao where TipoMovimentacao like ?";
        
        try {
            
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText()+"%");
            rs = pst.executeQuery();
            tblMovimentacao.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, error);

        }
        

    }

    public void ListaMovimentacao() {
        String sql = "select * from movimentacao order by idMovimentacao asc";
        try {

            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tblMovimentacao.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public void CadastrarMovimentacao() {
        String sql = "insert into movimentacao(TipoMovimentacao,Produto,Responsavel) values(?,?,?)";

        try {

            pst = conecta.prepareStatement(sql);
            pst.setString(1, (String)ComboTipo.getSelectedItem());
            pst.setString(2, (String)ComboProduto.getSelectedItem());
            pst.setString(3, (String)ComboResponsavel.getSelectedItem());
            pst.execute();
            ComboTipo.setEnabled(false);
            ComboProduto.setEnabled(false);
            ComboResponsavel.setEnabled(false);
            btnSalvar.setEnabled(false);
            

            JOptionPane.showMessageDialog(null, "Criado com sucesso!!");
            ListaMovimentacao();

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
        ComboTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        ComboProduto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        ComboResponsavel = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimentacao = new javax.swing.JTable();
        btnSalvaEdicao = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Movimentação");

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar");

        jLabel1.setText("Codigo");

        txtCodigo.setEnabled(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo");

        ComboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Saida" }));
        ComboTipo.setEnabled(false);

        jLabel3.setText("Produto");

        ComboProduto.setEnabled(false);

        jLabel5.setText("Responsavel");

        ComboResponsavel.setEnabled(false);

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

        tblMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMovimentacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMovimentacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMovimentacao);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboResponsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSalvaEdicao)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(ComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ComboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvaEdicao)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        PesquisarMovimentacao();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        LimparTela();
        NovoMovimentacao();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        CadastrarMovimentacao();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        EditarMovimentacao();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        ExcluirMovimentacao();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        LimparTela();
        ComboTipo.setEnabled(false);
        ComboProduto.setEnabled(false);
        ComboResponsavel.setEnabled(false);
        btnSalvar.setEnabled(false);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblMovimentacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMovimentacaoMouseClicked
        MostrarItens();
    }//GEN-LAST:event_tblMovimentacaoMouseClicked

    private void btnSalvaEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaEdicaoActionPerformed

        SalvarEdicaoMovimentacao();
        LimparTela();
    }//GEN-LAST:event_btnSalvaEdicaoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboProduto;
    private javax.swing.JComboBox<String> ComboResponsavel;
    private javax.swing.JComboBox<String> ComboTipo;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMovimentacao;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

}
