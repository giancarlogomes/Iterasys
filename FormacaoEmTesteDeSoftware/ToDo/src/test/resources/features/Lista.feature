#language: pt
#encoding: utf-8
  Funcionalidade: Lista
    Criar e manter itens em uma lista

  Esquema do Cenario: Incluir itens na lista
    Dado que estou na Lista de compras
    Quando digito <item>
    E pressiono Enter
    Entao exibe o item na Lista de compras
    Exemplos:
      | item              |
      | "Macarrão"        |
      | "Molho de tomate" |

  Esquema do Cenario: Alterar itens na lista
    Dado que estou na Lista de compras
    Quando clico em <item>
    E clico duas vezes sobre o nome <item> na barra ao lado direito do site
    E digito novo nome para o item e pressiono Enter
    Entao o nome do item é alterado
    Exemplos:
      | item              |
      | "Macarrão"        |
      | "Molho de tomate" |

  Esquema do Cenario: Consultar itens na Lista
    Dado que estou em Microsoft ToDo
    E logado
    Quando clico na Lista de compras
    Entao visualizo os <item>
    Exemplos:
      | item              |
      | "Macarrão"        |
      | "Molho de tomate" |

  Esquema do Cenario: Excluir itens na lista
    Dado que estou na Lista de compras
    Quando clico com o botao direito em <item>
    E clico em Excluir tarefa
    Entao visualizo a mensagem de confirmacao
    Quando clico em Excluir tarefa
    Entao <item> é deletado da Lista de compras
    Exemplos:
      | item              |
      | "Macarrão"        |
      | "Molho de tomate" |