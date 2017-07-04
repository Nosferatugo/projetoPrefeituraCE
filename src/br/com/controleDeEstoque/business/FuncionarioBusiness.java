/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.business;

import br.com.controleDeEstoque.commom.exception.BusinessException;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.dao.FuncionarioDAO;
import br.com.controleDeEstoque.dao.IFuncionarioDAO;
import br.com.controleDeEstoque.enuns.EstadoCivil;
import br.com.controleDeEstoque.enuns.Sexo;
import br.com.controleDeEstoque.facade.Facade;
import br.com.controleDeEstoque.telas.TelaCadastrarFuncionarios;
import br.com.controleDeEstoque.model.Contato;
import br.com.controleDeEstoque.model.Endereco;
import br.com.controleDeEstoque.model.Funcionario;
import br.com.controleDeEstoque.model.Rg;
import br.com.controleDeEstoque.strategy.MensagensUtilStrategy;
import br.com.controleDeEstoque.strategy.UtilitariosStrategy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Onismar
 */
public class FuncionarioBusiness implements IFuncionarioBusiness {

    private IFuncionarioDAO funcionarioDao;

    public FuncionarioBusiness() {
        funcionarioDao = new FuncionarioDAO();
    }

    @Override
    public void salvar(Funcionario f) throws ExceptionDAO, BusinessException {
        funcionarioDao.salvar(f);
    }

    @Override
    public void atualizar(Funcionario f) throws ExceptionDAO, BusinessException {
        funcionarioDao.atualizar(f);
    }

    @Override
    public Funcionario getById(Long id) throws ExceptionDAO, BusinessException {
        return funcionarioDao.getById(id);
    }

    @Override
    public List<Funcionario> getTodos() throws ExceptionDAO, BusinessException {
        return funcionarioDao.getTodos();
    }

    @Override
    public void modificaStatus(Funcionario f, boolean status) throws ExceptionDAO, BusinessException {
        funcionarioDao.modificaStatus(f, status);
    }

    @Override
    public List<Funcionario> autorizacaoDesconto(boolean status) throws ExceptionDAO, BusinessException {
        return funcionarioDao.autorizacaoDesconto(status);
    }

    @Override
    public List<Funcionario> statusFuncionario(boolean status) throws ExceptionDAO, BusinessException {
        return funcionarioDao.statusFuncionario(status);
    }

    @Override
    public void realizarCadastroFuncionario(Long id, String nome, String cpf, String rg, String dataNascimento,
            String dataExpedicao, String orgaoEmissor, String rua, String numeroDaRua, String bairro, String cep,
            String cidade, String estado, List<Contato> listaContatos, Sexo sexo, EstadoCivil estadoSivil) throws ExceptionDAO, BusinessException {

        TelaCadastrarFuncionarios cf = new TelaCadastrarFuncionarios(null, true);
        UtilitariosStrategy utilitarios = new UtilitariosStrategy();

        Rg rg1 = new Rg();
        rg1.setNumero(rg);
        rg1.setDataExpedicao(utilitarios.pasarStringParaCalendar(dataExpedicao));
        rg1.setOrgao(orgaoEmissor);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setNumero(numeroDaRua);
        endereco.setCep(cep);

        Funcionario f = new Funcionario();
        f.setId(id);
        f.setNome(nome);
        f.setCpf(cpf);

        f.setRg(rg1);
        f.setDataCadastro(Calendar.getInstance());
        f.setDataNascimento(utilitarios.pasarStringParaCalendar(dataNascimento));

        f.setEstadoCivil(estadoSivil);
        f.setSexo(sexo);
        f.setStatus(true);
        f.setContato(listaContatos);
        f.setEndereco(endereco);
        System.out.println(id);
        if (id == null) {
            salvar(f);

            JOptionPane.showMessageDialog(null, MensagensUtilStrategy.getValor(MensagensUtilStrategy.MSG_SUCESSO_SALVAR, "  "));
            cf.dispose();

        } else if (id != null) {

            atualizar(f);
            JOptionPane.showMessageDialog(null, MensagensUtilStrategy.getValor(MensagensUtilStrategy.MSG_ALTERAR, "  "));
            cf.dispose();
        }

    }

    @Override
    public Funcionario retornaFuncionario(String cpf) throws ExceptionDAO, BusinessException {
        return funcionarioDao.retornaFuncionario(cpf);
    }

    @Override
    public void gerarTabelaFuncionario(JTable tabela) throws BusinessException {

        DefaultTableModel modelo = new DefaultTableModel();
        Funcionario f = new Funcionario();

        modelo = (DefaultTableModel) tabela.getModel();

        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Função");
        modelo.addColumn("Usuário");
        modelo.addColumn("Senha");
        modelo.addColumn("Nível");

        //Definindo o tamanho das colunas
        tabela.getColumnModel().getColumn(1).setPreferredWidth(280);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(100);

        //Definindo tamanho 0 a coluna ID
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);

        tabela.setRowHeight(25);
        modelo.setNumRows(0);

        List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

        try {
            listaFuncionario = getTodos();
        } catch (Exception ex) {

        }

        if (!listaFuncionario.isEmpty()) {

            for (int i = 0; i < listaFuncionario.size(); i++) {
                Vector linha = new Vector();
                linha.add(listaFuncionario.get(i).getId());
                linha.add(listaFuncionario.get(i).getNomeFuncionario());
                linha.add(listaFuncionario.get(i).getCpfFuncionario());
                linha.add(listaFuncionario.get(i).getFuncaoFuncionario());
                linha.add(listaFuncionario.get(i).getLogin());
                linha.add(listaFuncionario.get(i).getSenha());
                linha.add(listaFuncionario.get(i).getNivelAcesso());
                modelo.addRow(linha);

            }

        }

    }

    @Override
    public void gerarTelaEditarFuncionario(Funcionario f) throws BusinessException {

        TelaCadastrarFuncionarios cf = new TelaCadastrarFuncionarios(null, true);
        UtilitariosStrategy fd = new UtilitariosStrategy();

        cf.jTextFieldNome.setText(f.getNomeFuncionario());
        String nivel = String.valueOf(f.getNivelAcesso());
        cf.jComboBoxNivelDeAcesso.setSelectedItem(nivel);
        cf.jFormattedTextFieldCpf.setText(f.getCpfFuncionario());
        cf.jComboBoxFuncao.setSelectedItem(f.getFuncaoFuncionario());
        cf.jFormattedTextFieldContato.setText(f.getContatoFuncionario());
        cf.jTextFieldUsuario.setText(f.getLogin());
        cf.jTextFieldSenha.setText(f.getSenha());
        cf.id = f.getId();


        cf.funcao = "editar";
       

        cf.setVisible(true);

    }

    @Override
    public Funcionario retornaFuncionarioPorLogin(String login) throws ExceptionDAO, BusinessException {
        return funcionarioDao.retornaFuncionarioPorLogin(login);

    }

    @Override
    public Boolean confirmaFuncionarioPorLogin(String login, String senha) throws ExceptionDAO, BusinessException {
        Funcionario func = new Funcionario();
        Boolean status = null;
        func = retornaFuncionarioPorLogin(login);
        if (func == null) {
            JOptionPane.showMessageDialog(null, "Login não cadastrado");
            status = false;
        } else {
            if (func.getSenha().equals(senha)) {
                status = true;
            } else {
                JOptionPane.showMessageDialog(null, "Senha incoreta");
                status = false;
            }
        }

        return status;
    }

    @Override
    public void remover(Funcionario f) throws ExceptionDAO {
       funcionarioDao.remover(f);
    }

}
