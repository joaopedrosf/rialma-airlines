<h1 align="center">Rialma Airlines</h1>

# Índice
- [Sobre](#-📖-Sobre)
- [Desenvolvimento / usabilidade](#-💻-Desenvolvimento-/-usabilidade)
- [Tecnologias utilizadas](#-🚀-Tecnologias)
- [Como usar o projeto](#-📂-Como-usar-o-projeto)

---

## 📖 Sobre
O **Rialma AirLines** é sistema de empresa aérea fictícia desenvolvido para o trabalho final da disciplina "Algoritmos e Programação 2" na UFG-GO. O programa é feito puramente em Java sem o uso de qualquer biblioteca externa ou frameworks, visando demonstrar os conhecimentos em **POO (Programação Orientada a Objetos)**.

---

## 💻 Desenvolvimento / usabilidade
Nesse programa, o usuário pode fazer um cadastro, ver os vôos e comprar uma passagem caso tenha efetuado o cadastro. Vale ressaltar que todas as lógicas de verificações durante o cadastro são feitas no próprio programa, como por exemplo: verificação do CPF, data de validade do cartão, limite/saldo disponível do cartão de crédito ou débito. Além disso, todas as exceções são tratadas durante toda a execução do programa. Segue abaixo uma imagem do cadastro:

<div align="center">
<img src="https://ik.imagekit.io/b2twgpcgqmc/Rialma-Airlines/Screenshot_34_1FUX8kOY_.png" />
</div>

Após feito o cadastro, o cadastro fica salvo em um arquivo "Cadastro.txt". Para tal implementação, usamos as classes **FileReader** e **BufferedReader**. Segue abaixo a demonstração:

<div align="center">
<img src="https://ik.imagekit.io/b2twgpcgqmc/Rialma-Airlines/Screenshot_35_t6Gy0zR1j.png" />
</div>

O usuário pode também optar por fazer uma compra caso seja cadastrado, e toda a lógica de verificação do usuário cadastrado também é feita pelo próprio programa, usaremos o cadastro feito anteriormente para demonstração:

<div align="center">
<img src="https://ik.imagekit.io/b2twgpcgqmc/Rialma-Airlines/Screenshot_37_nNzpiBmA1l.png" />
</div>

---
 ## 🚀 Tecnologias utilizadas

 O projeto foi feito inteiramente em **Java** puro, sem nenhuma biblioteca externa ou framework. O objetivo do projeto era abordar e demonstrar os conhecimentos em **POO (Programação Orientada a Objetos)**

---
 ## 📂 Como usar o projeto
 Para usar o projeto é necessário ter o Java instalado em sua máquina e executar os seguintes comandos no terminal:
```bash
#Clonar o repositório para sua máquina
$ git clone https://github.com/eliasnepo/rialma-airlines.git

#Entrar no diretório do projeto
$ cd bin

#Executar a aplicação
$ java aplicacao/Sistema
```

---

Desenvolvido por Elias Nepomuceno e João Pedro Silva Franco.