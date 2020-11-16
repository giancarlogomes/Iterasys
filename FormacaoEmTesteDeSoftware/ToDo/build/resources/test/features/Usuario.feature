#language: pt
#encoding: utf-8
  Funcionalidade: Usuario
    Criar, manter, e autenticar usuarios

  Cenario: Login com sucesso
    Dado que estou em Microsoft ToDo
    E deslogado
    Quando clico no icone para realizar login
    Entao exibe a pagina de login
    Quando preencho o e-mail "giancarlo.gomes@hotmail.com"
    E clico em Proximo
    E preencho a senha CORRETA
    E clico em Entrar
    Entao o site realiza login

  Cenario: Login com senha incorreta
    Dado que estou em Microsoft ToDo
    E deslogado
    Quando clico no icone para realizar login
    Entao exibe a pagina de login
    Quando preencho o e-mail "giancarlo.gomes@hotmail.com"
    E clico em Proximo
    E preencho a senha INCORRETA
    E clico em Entrar
    Entao o site exibe mensagem de erro

  Cenario: Criar nova conta
    Dado que estou em Microsoft ToDo
    E nao possuo conta na Microsoft
    Quando clico no icone para realizar login
    Entao exibe a pagina de login
    Quando clico em Crie uma!
    E preencho o e-mail "giancarlo.gomes@hotmail.com" e clico em Proximo
    E preencho a senha "Teste@123" e clico em Proximo
    E preencho o Nome "Giancarlo" e o Sobrenome "Gomes" e clico em Proximo
    E preencho o Pais "Brasil"
    E preencho o Dia "1" e o Mes "abril" e o Ano "1997" e clico em Proximo
    E preencho o codigo de verificacao enviado para o e-mail e clico em Proximo
    Entao o site cria a conta com sucesso

  Cenario: Recuperacao de senha
    Dado que estou em Microsoft ToDo
    E deslogado
    Quando clico no icone para realizar login
    Entao exibe a pagina de login
    Quando preencho o e-mail "giancarlo.gomes@hotmail.com"
    E clico em Proximo
    Quando clico em Esqueceu a senha?
    E seleciono a opcao de receber SMS no celular cadastrado
    E preencho os 4 ultimos digitos do celular cadastrado e clico em Obter codigo
    Entao recebo o codigo de verificacao para cadastrar nova senha em meu celular