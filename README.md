![Logo](https://i.ibb.co/MTWpHzn/logo.png)
# Massey Ferguson: Semente

Este aplicativo Android, desenvolvido no Android Studio, auxilia na configuração de plantadeiras Massey Ferguson, calculando as configuração ideal de engrenagens para a distribuição de sementes solicitada pelo usuário.

A verfificação na tabela da plantadeira é demorada e limitada a alguns discos especificos. Essa ferramenta se adapta a todos os discos.
## Funcionalidades

- Cálculo preciso das engrenagens com base no número de sementes por metro linear e número de furos no disco.
- Opções de ajuste para encontrar a configuração mais adequada.
- Interface intuitiva e fácil de usar.
## Algoritmo

Esse aplicativo recebe o valor de sementes na qual se deseja distribuir por metro linear e o número de furos no disco. Com essas informações e uma constante aqui nomeada *ValorMF* que representa a relação base entre a roda da plantadeira e a base do disco, é buscada todas as combinações de engrenagens possiveis, então são apresentados os valores mais próximos do objetivo.
## Como Usar

Primeiro informe o número de sementes por metro linear que você deseja distribuir. Depois o número de furos no disco que esta sendo utilizado. Após isso é só apartar em "Calcular"

![Tela Inical](https://i.ibb.co/chhv341m/tutorial-1.png)

Como nem sempre o valor exato pode ser obtido alguns parâmetros de ajustes podem ser usados.
Pode ser buscado o valor mais próximo a cima ou mais próximo abaixo do valor inserido.

![Acima Abaixo](https://i.ibb.co/ksGDvq82/tutorial-2.png)

Esses parâmetros podem ser ajustadas rapidamente e assim o operador decide qual se aqueda melhor ao seu trabalho.

Ele foi desenvolvido com base na plantadeira Massey Ferguson MF 409L, mas creio que o mesmo deve ser compatível com os outros modelos da mesma linha, bem como maquinas Valtra.


O aplicativo vai mostrar qual engrenagem deve estar em cada eixo para se obter o resultado solicitado, bem como qual será o valor real de sementes que serão distribuidas por metro linear.

![Resultado](https://i.ibb.co/JR6jhNTX/tutorial-3.png)
## Licença

[Mozilla Public License 2.0](https://choosealicense.com/licenses/mpl-2.0/)
## Direito de imagem e marca

Todas as marcas pertencem a AGCO Corporation
[AGCO Corporation](https://www.agcocorp.com/)