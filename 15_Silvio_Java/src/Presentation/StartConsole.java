/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Business_code.GestorMoedas;
import java.util.Scanner;

/**
 *
 * @author silvio
 */
public class StartConsole {
    
    public static void main(String[] args) {
        
        GestorMoedas gm = null;
        try {
            gm = new GestorMoedas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        Scanner scanner = new Scanner(System.in);
        String opcao;
        
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Sair");
            System.out.println("2 - Depositar moeda");
            System.out.println("3 - Mudar câmbio");
            System.out.println("4 - Calcular valor da carteira");
            System.out.println("5 - Calcular valor por moeda");
            System.out.println("6 - Visão Global");
            System.out.println("7 - Guardar token");
            System.out.println("8 - Levantar moeda");
            
            opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    System.out.println("Saindo...");
                    break;
                case "2": {
                    System.out.println("Depositar moeda selecionado");
                    System.out.println("Identificador da moeda: ");
                    String idmoeda = scanner.nextLine();
                    System.out.println("Token: ");
                    String token = scanner.nextLine();
                    System.out.println("Quantidade: ");
                    int qntde = scanner.nextInt();
                    scanner.nextLine();
                    
                    try {
                        gm.depositaMoeda(idmoeda, token, qntde);
                        System.out.println("Token validado. Quantia depositada.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "3": {
                    System.out.println("Mudar câmbio selecionado");
                    System.out.println("Identificador da moeda a ser alterada: ");
                    String idmoeda = scanner.nextLine();
                    System.out.println("Valor do cambio novo (use virgula como separador) : ");
                    double cambio = -1;
                    try {
                        cambio = scanner.nextDouble();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("cambio invalido");
                    }
                    
                    if (cambio > 0) {
                        try {
                            gm.alteraCambio(idmoeda, cambio);
                            System.out.println("Cambio alterado.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else{
                        scanner.nextLine();
                    }
                    
                    break;
                }
                case "4":
                    System.out.println("Calcular valor da carteira selecionado");
                    System.out.println("O total da carteira é : €" + gm.totalCarteira());
                    break;
                case "5":
                    System.out.println("Calcular valor por moeda selecionado");
                    System.out.println(gm.valorPorMoeda());
                    break;
                case "6":
                    System.out.println("Visão Global selecionada");
                    System.out.println(gm.toString());
                    break;
                case "7": {
                    System.out.println("Guardar token selecionado");
                    System.out.println("Identificador da moeda: ");
                    String idmoeda = scanner.nextLine();
                    System.out.println("Token anterior: ");
                    String tokenOld = scanner.nextLine();
                    if (gm.validaToken(idmoeda, tokenOld)) {
                        System.out.println("token validado. Defina o novo token: ");
                        String tokenNew = scanner.nextLine();
                        try {
                            gm.alteraToken(idmoeda, tokenNew);
                            System.out.println("token redefinido.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("token nao validado");
                    }
                    break;
                }
                case "8": {
                    System.out.println("Levantar moeda selecionado");
                    System.out.println("Identificador da moeda: ");
                    String idmoeda = scanner.nextLine();
                    System.out.println("Token: ");
                    String token = scanner.nextLine();
                    System.out.println("Quantidade: ");
                    int qntde = scanner.nextInt();
                    scanner.nextLine();
                    
                    try {
                        gm.levantaMoeda(idmoeda, token, qntde);
                        System.out.println("Token validado. Quantia levantada.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
            
        } while (!opcao.equals("1"));
        
        scanner.close();
    }
}
