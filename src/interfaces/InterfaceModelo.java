package interfaces;

import java.util.ArrayList;

public interface  InterfaceModelo {
    ArrayList<Object> objs = new ArrayList<>();
    Object obj = new Object();

    public String salvar(Object o);

    public String atualizar(Object o);
    
    public String devolucao(int id);

    public String excluir(int id);

    public ArrayList<Object> consultarTodos();

    public ArrayList<Object> consultar(String criterio);
}
