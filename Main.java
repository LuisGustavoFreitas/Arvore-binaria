import java.util.Scanner;

// Classe que representa cada nó da árvore
class Nodo {
    char caractere;
    Nodo esquerda;
    Nodo direita;

    Nodo(char caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.direita = null;
    }
}

// Classe principal da Árvore Binária de Código Morse
class ArvoreBinariaMorse {
    Nodo raiz;

    // Inicializa a árvore com um nó raiz vazio
    void inicializar() {
        raiz = new Nodo(' ');
        inserirAutomatico();
    }

    // Inserção manual de um caractere conforme o código Morse
    void inserir(String codigo, char caractere) {
        Nodo atual = raiz;
        int tamanho = codigo.length();
        int i = 0;

        while (i < tamanho) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new Nodo(' ');
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    atual.direita = new Nodo(' ');
                }
                atual = atual.direita;
            }
            i = i + 1;
        }

        atual.caractere = caractere;
    }

    // Busca o caractere correspondente a um código Morse
    char buscar(String codigo) {
        Nodo atual = raiz;
        int tamanho = codigo.length();
        int i = 0;

        while (i < tamanho) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                atual = atual.direita;
            }

            if (atual == null) {
                return '?';
            }

            i = i + 1;
        }

        return atual.caractere;
    }

    // Exibe a árvore hierarquicamente
    void exibirArvore(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        exibirArvore(nodo.direita, nivel + 1);

        int i = 0;
        while (i < nivel) {
            System.out.print("   ");
            i = i + 1;
        }

        System.out.println(nodo.caractere);

        exibirArvore(nodo.esquerda, nivel + 1);
    }

    // Converte texto normal para código Morse
    void textoParaMorse(String texto) {
        int i = 0;
        while (i < texto.length()) {
            char letra = texto.charAt(i);
            if (letra != ' ') {
                String codigo = procurarCodigo(raiz, letra, "");
                System.out.print(codigo + " ");
            }
            i = i + 1;
        }
        System.out.println();
    }

    // Procura o código Morse de um caractere (recursivamente)
    String procurarCodigo(Nodo nodo, char letra, String caminho) {
        if (nodo == null) {
            return "";
        }

        if (nodo.caractere == letra) {
            return caminho;
        }

        String esquerda = procurarCodigo(nodo.esquerda, letra, caminho + ".");
        if (esquerda.length() > 0) {
            return esquerda;
        }

        String direita = procurarCodigo(nodo.direita, letra, caminho + "-");
        if (direita.length() > 0) {
            return direita;
        }

        return "";
    }

    // Converte código Morse em texto normal
    void morseParaTexto(String codigoMorse) {
        int i = 0;
        String atual = "";
        while (i < codigoMorse.length()) {
            char c = codigoMorse.charAt(i);
            if (c != ' ') {
                atual = atual + c;
            } else {
                if (atual.length() > 0) {
                    System.out.print(buscar(atual));
                    atual = "";
                }
            }
            i = i + 1;
        }

        if (atual.length() > 0) {
            System.out.print(buscar(atual));
        }

        System.out.println();
    }

    // Insere automaticamente o alfabeto e os números
    void inserirAutomatico() {
        inserir(".-", 'A');
        inserir("-...", 'B');
        inserir("-.-.", 'C');
        inserir("-..", 'D');
        inserir(".", 'E');
        inserir("..-.", 'F');
        inserir("--.", 'G');
        inserir("....", 'H');
        inserir("..", 'I');
        inserir(".---", 'J');
        inserir("-.-", 'K');
        inserir(".-..", 'L');
        inserir("--", 'M');
        inserir("-.", 'N');
        inserir("---", 'O');
        inserir(".--.", 'P');
        inserir("--.-", 'Q');
        inserir(".-.", 'R');
        inserir("...", 'S');
        inserir("-", 'T');
        inserir("..-", 'U');
        inserir("...-", 'V');
        inserir(".--", 'W');
        inserir("-..-", 'X');
        inserir("-.--", 'Y');
        inserir("--..", 'Z');
        inserir("-----", '0');
        inserir(".----", '1');
        inserir("..---", '2');
        inserir("...--", '3');
        inserir("....-", '4');
        inserir(".....", '5');
        inserir("-....", '6');
        inserir("--...", '7');
        inserir("---..", '8');
        inserir("----.", '9');
    }
}

// Classe principal com menu de interação
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n=== MENU CÓDIGO MORSE ===");
            System.out.println("1 - Exibir árvore");
            System.out.println("2 - Texto para Código Morse");
            System.out.println("3 - Código Morse para Texto");
            System.out.println("4 - Buscar caractere");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine(); // limpa buffer

            if (opcao == 1) {
                arvore.exibirArvore(arvore.raiz, 0);
            } else if (opcao == 2) {
                System.out.print("Digite o texto: ");
                String texto = entrada.nextLine().toUpperCase();
                System.out.print("Código Morse: ");
                arvore.textoParaMorse(texto);
            } else if (opcao == 3) {
                System.out.print("Digite o código Morse (separe letras com espaço): ");
                String morse = entrada.nextLine();
                System.out.print("Texto traduzido: ");
                arvore.morseParaTexto(morse);
            } else if (opcao == 4) {
                System.out.print("Digite o código Morse: ");
                String codigo = entrada.nextLine();
                System.out.println("Caractere correspondente: " + arvore.buscar(codigo));
            } else if (opcao == 5) {
                System.out.println("Encerrando programa...");
            } else {
                System.out.println("Opção inválida!");
            }
        }

        entrada.close();
    }
}
