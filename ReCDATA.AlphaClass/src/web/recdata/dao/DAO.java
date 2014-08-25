package web.recdata.dao;


import web.recdata.model.Entidade;

public interface DAO {
        
        /* Padr�es do CRUD */
        /* Cada classe do pacote DAO implementar� essa interface.
         * Cabe a cada classe cliente verificar se o objeto de entrada corresponde ao seu tipo pr�prio,
         * visto que todas as classes est�o abstra�das pela interface Entidade. Por essa raz�o, as fun��es
         * desse CRUD podem lan�ar a exce��o ClasseInvalidaException - Creditos Eri Jonh
         * */
        
        public void creat(Entidade entidade) ;
        
        public void readById(Entidade entidade) ;
        
        public void update(Entidade entidade);
        
        public void delete(Entidade entidade);
        
}
