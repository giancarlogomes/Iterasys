#language: pt
#enconding: utf8

Funcionalidade: Consultar Produto
  Como um cliente eventual, gostaria de consultar a disponibilidade e preço
  de alguns produtos que tenho interesse em adquirir

  Cenario: Consulta produto fixo
    Dado que acesso o site da Petz "1"
    Quando procuro por "Ração" e pressiono Enter
    Entao exibe a lista de produtos relacionados a "Ração"
    Quando seleciono o primeiro produto da lista
    Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

  Cenario: Consulta produto fixo com clique na Lupa
    Dado que acesso o site da Petz "2"
    Quando procuro por "Ração" e clico na Lupa
    Entao exibe a lista de produtos relacionados a "Ração"
    Quando seleciono o primeiro produto da lista
    Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

  Cenario: Consulta produto fixo que nao existe com Enter
    Dado que acesso o site da Petz "3"
    Quando procuro por "randandandanrandandandan" e pressiono Enter
    Entao exibe uma lista de produtos relacionados e a mensagem de que nao encontrou "randandandanrandandandan"

  Cenario: Consulta produto fixo com menos de 3 letras
    Dado que acesso o site da Petz "4"
    Quando procuro por "ab" e pressiono Enter
    Entao exibe uma caixa de alerta informando que precisa preencher pelo menos tres letras na pesquisa

  Esquema do Cenario:
    Dado que acesso o site da Petz <id>
    Quando procuro por <produto> e pressiono Enter
    Entao exibe a lista de produtos relacionados a <produto>
    Quando seleciono o <produtoDescricao> da lista
    Entao verifico a <marca> com <precoNormal> e <precoAssinante>

    Exemplos:
      | id  | produto   | marca         | precoNormal | precoAssinante | produtoDescricao                                        |
      | "5" | "Ração"   | "Royal Canin" | "R$ 232,69" | "R$ 209,42"    | "Ração Royal Canin Maxi - Cães Adultos - 15kg"          |
      | "6" | "Petisco" | "Petz"        | "R$ 3,99"   | "R$ 3,59"      | "Snack Petz Cuidado Oral para Cães de Pequeno Porte"    |
      | "7" | "Petisco" | "Petz"        | "R$ 5,99"   | "R$ 5,39"      | "Snack Petz Cordeiro, Linhaça e Óleo de Coco para Cães" |
