package Presentation;

import API_presentation_business.ProgramController;
import Business.Contacto;
import Business.Pessoa;
import Business.Veiculo;
import Exception.InvalidException;
import Exception.NotFoundException;
import Exception.OversizeException;

import java.util.ArrayList;
import java.util.Scanner;

public class StartConsole {
    public static void main(String[] args) {
        ProgramController pc = new ProgramController();
        Scanner sc = new Scanner(System.in);
        String opcao = "";

        do {
            System.out.println("---- Menu ----");
            System.out.println("0 - Sair");
            System.out.println("1 - Adicionar pessoa");
            System.out.println("2 - Remover pessoa");
            System.out.println("3 - Adicionar veiculo");
            System.out.println("4 - Remover veiculo");
            System.out.println("5 - Listar pessoas");
            System.out.println("6 - Listar veiculos por pessoa");
            System.out.println("7 - Listar todos os veiculos");
            System.out.print("Escolha uma opção: ");

            opcao = sc.next();
            sc.nextLine();


            switch (opcao) {
                case "0":
                    System.out.println("Saindo...");
                    break;
                case "1": {
                    // Lógica para adicionar pessoa

                    // id da pessoa
                    System.out.println("ID da pessoa: ");
                    String idPessoa = null;
                    Pessoa pessoa = null;
                    boolean validado = false;
                    while (!validado) {
                        try {
                            idPessoa = sc.nextLine();
                            pessoa = new Pessoa(idPessoa);
                            validado = true;
                        } catch (InvalidException ex) {
                            System.out.printf(ex.getMessage() + "\n");
                        }
                    }

                    // nome da pessoa
                    validado = false;
                    System.out.println("Nome da pessoa: ");
                    while (!validado) {
                        String nome = sc.nextLine();
                        try {
                            pessoa.setNome(nome);
                            validado = true;
                        } catch (InvalidException ex) {
                            System.out.printf(ex.getMessage() + "\n");
                        }
                    }
                    // idade da pessoa
                    validado = false;
                    System.out.println("Idade da pessoa: ");
                    while (!validado) {
                        Integer idade = sc.nextInt();
                        sc.nextLine();
                        try {
                            pessoa.setIdade(idade);
                            validado = true;
                        } catch (InvalidException ex) {
                            System.out.printf(ex.getMessage() + "\n");
                        }
                    }


                    //contactos
                    Contacto contacto = null;
                    ArrayList<Contacto> arrayContactos = new ArrayList<>();
                    String opContacto;
                    do {
                        System.out.println("escolha o tipo de contacto a adicionar: ");
                        System.out.println("1- e-mail");
                        System.out.println("2- tel pessoal");
                        System.out.println("3- tel empresa");
                        System.out.println("0 - sair");

                        opContacto = sc.nextLine();
                        switch (opContacto) {
                            case "1": {
                                validado = false;
                                while (!validado) {
                                    System.out.println("email de contacto: ");
                                    String valor = sc.nextLine();
                                    try {
                                        contacto = new Contacto("e-mail", valor);
                                        validado = true;
                                        arrayContactos.add(contacto);
                                    } catch (InvalidException e) {
                                        System.out.println(e.getMessage() + "\n");
                                    }
                                }
                                break;
                            }
                            case "2": {
                                validado = false;
                                while (!validado) {
                                    System.out.println("numero do contacto: ");
                                    String valor = sc.nextLine();
                                    try {
                                        contacto = new Contacto("tel_pessoal", valor);
                                        validado = true;
                                        arrayContactos.add(contacto);
                                    } catch (InvalidException e) {
                                        System.out.println(e.getMessage() + "\n");
                                    }
                                }
                                break;
                            }
                            case "3": {
                                validado = false;
                                while (!validado) {
                                    System.out.println("numero do contacto: ");
                                    String valor = sc.nextLine();
                                    try {
                                        contacto = new Contacto("tel_empresa", valor);
                                        validado = true;
                                        arrayContactos.add(contacto);
                                    } catch (InvalidException e) {
                                        System.out.println(e.getMessage() + "\n");
                                    }
                                }
                                break;
                            }
                            case "0": {
                                if (arrayContactos.isEmpty()) {
                                    System.out.println("ao menos um contacto preicisa ser adicionado.");
                                }
                                break;
                            }
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (arrayContactos.isEmpty() || !opContacto.equals("0"));
                    pc.add(pessoa);
                    for (Contacto item : arrayContactos) {
                        try {
                            pc.add(idPessoa, item);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage() + "\n");
                        }

                    }

                    System.out.println("Pessoa e contactos adicionados.");
                    break;
                }
                case "2":{
                    // Lógica para remover pessoa
                    System.out.println("Id da pessoa a ser removida: ");
                    String idPessoa = sc.nextLine();
                    try {
                        pc.remove(idPessoa);
                        System.out.println("Pessoa removida com sucesso.");
                    }catch (NotFoundException e){
                        System.out.println(e.getMessage()+"\n");
                    }

                    break;}
                case "3": {
                    // Lógica para adicionar veículo
                    String idPessoa;
                    System.out.println("Id do dono do veiculo: ");
                    idPessoa = sc.nextLine();
                    if (!pc.existePessoa(idPessoa)) {
                        System.out.println("Pessoa nao encontrada. Adicione-a primeiro.");
                    } else {
                        //validar matricula e criar veiculo
                        String matricula;
                        Veiculo veiculo = null;
                        boolean validado = false;
                        do {
                            System.out.println("Insira a matricula do veiculo: ");
                            matricula = sc.nextLine();
                            try {
                                veiculo = new Veiculo(matricula);
                                validado = true;
                            } catch (InvalidException e) {
                                System.out.println(e.getMessage() + "\n");
                            }
                        } while (!validado);

                        //validar marca

                        validado = false;
                        do {
                            System.out.println("Insira a marca: ");
                            String marca = sc.nextLine();
                            try {
                                veiculo.setMarca(marca);
                                validado = true;
                            } catch (InvalidException e){
                                System.out.println(e.getMessage()+"\n");
                            }
                        } while (!validado);

                        //validar modelo

                        validado = false;
                        do {
                            System.out.println("Insira o modelo: ");
                            String modelo = sc.nextLine();
                            try {
                                veiculo.setModelo(modelo);
                                validado = true;
                            } catch (InvalidException e){
                                System.out.println(e.getMessage()+"\n");
                            }
                        } while (!validado);

                        //validar cilindrada

                        validado = false;
                        do {
                            System.out.println("Insira a cilindrada: ");
                            Integer cilindrada = sc.nextInt();
                            sc.nextLine();
                            try {
                                veiculo.setCilindrada(cilindrada);
                                validado = true;
                            } catch (InvalidException e){
                                System.out.println(e.getMessage()+"\n");
                            }
                        } while (!validado);

                        //validar numero chassi

                        validado = false;
                        do {
                            System.out.println("Insira o numero de chassi: ");
                            String chassi = sc.nextLine();
                            try {
                                veiculo.setNumeroChasi(chassi);
                                validado = true;
                            } catch (InvalidException e){
                                System.out.println(e.getMessage()+"\n");
                            }
                        } while (!validado);

                        //validar numero de lugares

                        validado = false;
                        do {
                            System.out.println("Insira o numero de lugares: ");
                            Integer lugares = sc.nextInt();
                            sc.nextLine();
                            try {
                                veiculo.setNumeroLugares(lugares);
                                validado = true;
                            } catch (InvalidException e){
                                System.out.println(e.getMessage()+"\n");
                            }
                        } while (!validado);

                        //validar numero de portas
                        validado = false;
                        do {
                            System.out.println("Insira o numero de portas: ");
                            Integer portas = sc.nextInt();
                            sc.nextLine();
                            try {
                                veiculo.setNumeroPortas(portas);
                                validado = true;
                            } catch (InvalidException e){
                                System.out.println(e.getMessage()+"\n");
                            }
                        } while (!validado);

                        //adicionar veiculo

                        try {
                            pc.add(idPessoa, veiculo);
                            System.out.println("veiculo adicionado.");
                        }catch (OversizeException e){
                            System.out.println(e.getMessage()+"\n");
                        }
                    }
                    break;
                }
                case "4":{
                    // Lógica para remover veículo
                    System.out.println("Id do dono do veiculo a ser removido: ");
                    String idDono = sc.nextLine();
                    if (pc.existePessoa(idDono)){
                        System.out.println("Matricula do veiculo: ");
                        String matricula = sc.nextLine();
                        try {
                            pc.remove(idDono, matricula);
                            System.out.println("Veiculo removido.");
                        } catch (NotFoundException e){
                            System.out.println(e.getMessage()+"\n");
                        }
                    }
                    else{
                        System.out.println("Pessoa nao encontrada.");
                    }
                    break;}
                case "5":
                    // Lógica para listar pessoas
                    System.out.println(pc.listarPessoas());
                    break;
                case "6": {
                    // Lógica para listar veículos por pessoa
                    System.out.println("id da pessoa: ");
                    String idPessoa = sc.nextLine();
                    try {
                        String listaVeiculos = pc.listarVeiculos(idPessoa);
                        if (listaVeiculos.isEmpty()) {
                            System.out.println("pessoa nao possui veiculos.");
                        } else {
                            System.out.println(listaVeiculos);
                        }
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }

                break;

                case "7":
                    // Lógica para listar veículos
                    System.out.println(pc.listarVeiculos());
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            System.out.println();
        } while (!opcao.equals("0"));

        sc.close();
    }
}
