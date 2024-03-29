package br.edu.ifpb.recdata.util;

public class Constantes {

	// mensagens de Sucesso
	public static String USUARIO_CADASTRADO = "USU�RIO CADASTRADO COM SUCESSO!";
	// TODO: troca por um dialog alert

	public static String RESERVA_CONCLUIDA = "RESERVA CONCLUIDO COM SUCESSO!";
	public static String ITEM_ENCONTRADO = "ITEM ENCONTRADO!";
	public static String RESERVA_ENCONTRADA = "RESERVA ENCONTRADO!";
	public static String EXISTE_CONEXAO = "CONEX�O ESTABELECIDA COM SUCESSO!";
	public static String USUARIO_EXISTE = "BEM VINDO(A) ";

	// mensagens de erros
	public static String USUARIO_NAO_CADASTRADO = "N�O FOI POSSIVEL CADASTRAR USU�RIO!";
	public static String RESERVA_NAO_CONCLUIDA = "N�O FOI POSSIVEL FAZER A RESERVA!";
	public static String ITEM_NAO_ENCONTRADO = "ITEM N�O ENCONTRADO!";
	public static String RESERVA_NAO_ENCONTRADA = "RESERVA N�O ENCONTRADO!";
	public static String NA0_EXISTE_CONEXAO = "N�o foi poss�vel estabelecer uma conex�o, verifique sua rede.";
	public static String ERRO_LOGAR = "SENHA/LOGIN ERRADO!";
	public static String ERROR_TIPOUSUARIO_NULL = "N�o foi poss�vel encontra os tipos de Usu�rio";

	// Campos n�o preenchidos corretamente
	public static String MSG_ErroPreencheCampo = "CAMPO N�O PREENCHIDO";
	public static String MSG_ErroSpinnerEscolha = "Por Favor, Selecione outro item da Lista..";
	public static String MSG_ErroSenhaDiferentes = "As senhas n�o correspondem";
	public static String MSG_ErroEmailInvalido = "E-mail inv�lido";
	public static String MSG_ErroTamanhoInvalidoNome = "Campo com Tamanho inv�lido(MIN 8/Max 40)";
	public static String MSG_ErroCaracterEspecial = "Campo N�O permite caracteres especiais";
	public static String MSG_ErroTamanhoInvalidoCPF = "Tamanho inv�lido(Min,Max 11)";
	public static String MSG_ErroTamanhoInvalidoFone = "Tamanho inv�lido(Max 10)";
	public static String MSG_ErroIdadeInvalida = "Idade entre 14 - 100 Anos";
	public static String MSG_ErroCampoItemInvalido = "Descri��o do Item n�o preenchida!";
	public static String MSG_ErroCampoData = "Data n�o Informada!";
	public static String MSG_ErroCampoHora = "Hor�rio n�o Informado!";
	public static String MSG_ErroCampoDataFinalAntes = "Data de Termino antes da inicial!";
	public static String MSG_ErroCampoDataInicioDepois = "Data Inicio Ap�s a da Final!";
	public static String MSG_ErroCampoHoraFinalAntes = "Hora Final antes da Hora Inicial";
	public static String MSG_ErroCampoHorasEquals = "Hora Inicial e Final iguais!";
	public static String MSG_ErroIntervaloMaior ="Tempo de Reserva maior que o permitido!" +
			"\n Tempo M�ximo 15 horas(07:00:00 �s 22:00:00)!";
	public static String MSG_ErroIntervaloIgualZERO ="Invervalo n�o pode ser Igual a ZERO!!";

	// String para avalia��o do conte�do dos campos
	public static final String STRING_PATTERN = "[0-9a-zA-Z��������������������������������� ,-]*";
	public static final String PASSWORD_PATTERN = "(?=.*[0-9@#$%^&+=])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}";
}
