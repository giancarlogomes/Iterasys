#language: pt
#encoding: utf-8

Funcionalidade: Cadastra Usuario
  Como um cliente eventual, gostaria de me cadastrar no site da Petz

  Esquema do Cenario: Cadastra Usuario
    Dado que acesso o site da Petz <id>
    Quando passo o mouse por cima do icone de usuario e clico em Criar Conta
    Entao a tela de cadastro de usuario eh exibida
    Quando preencho os dados <nome> <email> <celular> <sexo> <dataNascimento> <cpf> <senha> e confirmo a senha e clico em Criar Conta
    Entao eh exibido o aviso "Dados salvos com sucesso" e clico em Entendi
    Exemplos:
      | id  | nome                              | email                                         | celular       | sexo        | dataNascimento | cpf           | senha        |
      | "1" | "Jennifer Isabelle Eduarda Jesus" | "jenniferisabelleeduardajesus-76@maildrop.cc" | "91987915442" | "feminino"  | "10/12/1994"   | "15435075068" | "itjJCp96XP" |
      | "2" | "Luan Juan Martins"               | "luan_juan_martins2@maildrop.cc"              | "43935747791" | "masculino" | "11/03/2002"   | "85313865005" | "QhfLCKxRZy" |

  Esquema do Cenario: Realiza login com senha valida
    Dado que acesso o site da Petz <id>
    Quando passo o mouse por cime do icone de usuario e clico em Entrar
    Entao a tela de login eh exibida
    Quando preencho os dados <email> <senha> e clico em Entrar
    Entao o <usuario> eh logado com sucesso
    Exemplos:
      | id  | usuario    | email                                         | senha        |
      | "3" | "Jennifer" | "jenniferisabelleeduardajesus-75@maildrop.cc" | "itjJCp96XP" |
      | "4" | "Luan"     | "luan_juan_martins@maildrop.cc"               | "QhfLCKxRZy" |

  Esquema do Cenario: Realiza login com senha invalida
    Dado que acesso o site da Petz <id>
    Quando passo o mouse por cime do icone de usuario e clico em Entrar
    Entao a tela de login eh exibida
    Quando preencho os dados <email> <senha> e clico em Entrar
    Entao o <usuario> nao eh logado com sucesso
    Exemplos:
      | id  | usuario    | email                                         | senha          |
      | "5" | "Jennifer" | "jenniferisabelleeduardajesus-75@maildrop.cc" | "semsenha1"    |
      | "6" | "Luan"     | "luan_juan_martins@maildrop.cc"               | "senha$errada" |
