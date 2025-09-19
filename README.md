# Jogo-da-Forca

DEFINIÇÃO DO PROJETO

Desenvolvimento do Jogo da Forca com Java, Notação Z e Redes de Petri

Este projeto tem como objetivo desenvolver o Jogo da Forca, utilizando a linguagem de programação Java para sua implementação, em conjunto com técnicas formais de especificação e modelagem através da Notação z. 

Implementação em Java

A aplicação principal do projeto é a implementação do jogo da forca, em que o jogador tenta adivinhar uma palavra secreta, apenas fornecendo letras, com um número limitado de tentativas. A linguagem Java foi escolhida por ser uma linguagem que já estamos acostumados a trabalhar.
Na implementação, o jogo é estruturado em componentes modulares, tais como o controle das letras tentadas, verificação de acertos e erros, atualização da palavra secreta, limite de erros antes do enforcamento, exibição da palavra secreta, letras digitadas e desenho do boneco da forca e a lista de palavras a serem sorteadas.


REQUISITOS FUNCIONAIS

RF01: Iniciar o jogo
O sistema deve iniciar uma partida sorteando aleatoriamente uma palavra secreta de uma lista de palavras pré-definida.

RF02: Exibir palavra oculta
O sistema deve mostrar a palavra secreta ao jogador, substituindo as letras ainda não adivinhadas por linhas.

RF03: Permitir tentativa de letra
O sistema deve permitir que o jogador digite uma letra por tentativa.

RF04: Verificar acerto ou erro
O sistema deve verificar se a letra digitada pertence à palavra secreta.

RF05: Atualizar estado da palavra
Se a letra estiver correta, o sistema deve atualizar a palavra secreta revelando as posições corretas.

RF06: Controlar tentativas restantes
O sistema deve manter e exibir o número de tentativas incorretas restantes.

RF07: Detectar fim de jogo
O sistema deve identificar se o jogador descobriu a palavra ou atingiu o número máximo de erros.

RF08: Exibir mensagens de vitória ou derrota
O sistema deve exibir mensagens de vitória ou derrota ao final da partida.

RF09: Permitir reinício de jogo
Após o fim da partida, o sistema deve oferecer a opção de iniciar uma nova rodada.

RF10: Armazenar letras tentadas
O sistema deve manter e exibir as letras já tentadas pelo jogador.

