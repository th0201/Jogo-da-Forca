# Jogo-da-Forca

DEFINIÇÃO DO PROJETO

Desenvolvimento do Jogo da Forca com Java, Notação Z .

Este projeto tem como objetivo desenvolver o Jogo da Forca, utilizando a linguagem de programação Java para sua implementação, em conjunto com técnicas formais de especificação e modelagem através da Notação z. Desta forma,busca-se integrar teoria e prática no processo de construção de software,fortalecendo tanto o raciocínio lógico quanto a capacidade de especificação e implementação.  


Implementação em Java

A aplicação principal do projeto é a implementação do jogo da forca, em que o jogador tenta adivinhar uma palavra secreta, apenas fornecendo letras, com um número limitado de tentativas. A linguagem Java foi escolhida por ser uma linguagem que já estamos acostumados a trabalhar.
Na implementação, o jogo é estruturado em componentes modulares, tais como o controle das letras tentadas, verificação de acertos e erros, atualização da palavra secreta, limite de erros antes do enforcamento, exibição da palavra secreta, letras digitadas e desenho do boneco da forca e a lista de palavras a serem sorteadas.


 LINK PARA LISTA DE REQUISITOS :
 https://1drv.ms/w/c/3678062cb015dbde/EUrlZgc0wHpOrLjmEFhUbMkBIEl1MmyTZpnEJGngo-nGyg?e=MfhWAB

DIAGRAMA DE CLASSES

@startuml

class JogoDaForca {

    - palavraSecreta : String
    
    - tentativasRestantes : int
    
    - letrasCorretas : List
    
    - letrasErradas : List
    
    + iniciarJogo() : void
    
    + verificarLetra(letra : char) : boolean
    
    + verificarFimDeJogo() : boolean
    
    + realizarLogin(usuario : String, senha : String) : boolean
    
}

class Jogador {

    - nome : String
    
    - usuario : String
    
    - senha : String
    
    + Jogador(nome : String, usuario : String, senha : String)
    
    + autenticar(usuario : String, senha : String) : boolean
    
}

class ListaDePalavras {

    - palavras : List
    
    + sortearPalavra() : String
    
}

JogoDaForca --> Jogador : "possui"

JogoDaForca --> ListaDePalavras : "usa"


@enduml



DIAGRAMA DE CASOS DE USO

@startuml

left to right direction


actor Jogador

rectangle "Jogo da Forca" {

    (Realizar Login) as login
    
    (Iniciar Jogo) as iniciar_jogo
    
    (Escolher Letra) as escolher_letra
    
    (Mostrar Palavra Parcial) as parcial
    
    (Mostrar Tentativas Restantes) as tentativas
    
    (Verificar Vitória ou Derrota) as verificar
    
    (Mostrar Resultado Final) as resultado

    (Reiniciar Partida) as reiniciar
    
}

Jogador --> login

login --> iniciar_jogo : <<include>>

Jogador --> escolher_letra

Jogador --> reiniciar

escolher_letra --> parcial

escolher_letra --> tentativas

escolher_letra --> verificar

iniciar_jogo --> verificar

verificar --> resultado

@enduml



PLANO PARA A ESPECIFICAÇÃO FORMAL

A especificação formal do Jogo da Forca será desenvolvida utilizando a Notação Z, com o intuito de representar de maneira rigorosa e sem ambiguidades os estados e operações do sistema. O objetivo desse plano é estabelecer um roteiro claro para a modelagem formal, servindo de ponte entre a definição de requisitos e a especificação matemática completa.

Inicialmente, será definida a estrutura de estados do jogo, que inclui a palavra secreta escolhida, o conjunto de letras já tentadas, o número de erros cometidos, o limite máximo de erros permitidos e a situação atual da partida (em andamento, vitória ou derrota).
Em seguida, serão descritas as condições iniciais do sistema, que contemplam a ausência de letras tentadas no início da partida, o número de erros igual a zero e a escolha de uma palavra secreta sorteada a partir de uma lista pré-definida.

As operações principais a serem modeladas incluem: autenticação de usuário (cadastro e login), inicialização de uma nova partida, tentativa de uma letra, verificação de acertos e erros, atualização do estado da palavra, checagem das condições de vitória ou derrota e reinício de jogo após o término de uma rodada.

Além disso, serão estabelecidas restrições formais que garantem o funcionamento correto do sistema, como a proibição de repetição de letras já tentadas, a impossibilidade de ultrapassar o número máximo de erros permitido e a obrigatoriedade de que todas as letras tentadas pertençam ao alfabeto válido.

Por fim, a especificação em Z será estruturada em diferentes schemas: um schema de estado representando as variáveis principais do jogo, um schema de inicialização descrevendo o estado inicial e um conjunto de schemas de operações correspondentes às interações do jogador com o sistema.
Dessa forma, o plano assegura que a especificação formal seja organizada, consistente e adequada para guiar a implementação do jogo em Java.


ODS

O nosso jogo da forca foi desenvolvido com base na ODS 4: "Educação de Qualidade", que faz parte dos Objetivos de Desenvolvimento Sustentável da ONU. Essa ODS tem como propósito assegurar uma educação inclusiva e de qualidade, promovendo oportunidades de aprendizagem ao longo da vida para todos.

A partir dela, buscamos criar uma atividade lúdica que estimule o aprendizado de forma divertida e acessível, reforçando conteúdos e incentivando o raciocínio, a atenção e o desenvolvimento cognitivo. Assim, o jogo da forca contribui para tornar o processo educativo mais interativo e significativo.
