package com.loyola.paginasamarillas.Repositories;

import com.loyola.paginasamarillas.Models.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private static List<Company> companies = new ArrayList<>();

    static {
        companies.add(new Company(1,"Fast Food","Kentucky Fried Chicken", "Av. La Molina 192",
                "948543014","informes@kfc.com","https://www.kfc.com.pe/Online",
                "ic_kfc","KFC para chuparse los dedos!"));
        companies.add(new Company(2,"Fast Food","Bembos", "Los Portales de Santa Clara 145",
                "984125741","informes@bembos.com","https://www.bembos.com.pe/",
                "ic_bembos","Como Bembos, no hay otra"));
        companies.add(new Company(3,"Deporte","Adidas", "Jr. Unión 52 - Javier Prado Oeste",
                "716855115","informes@adidas.com","https://www.adidas.pe/",
                "ic_adidas","Imposible is nothing"));
        companies.add(new Company(4,"Deporte","Nike", "Av Los Frutales - 21",
                "948543049","informes@nike.com","www.lacasadeltacutacu.com",
                "ic_nike","Just do it"));
        companies.add(new Company(5,"Academia","Trilce", "Jr. Pelotillehue 53",
                "32541258","informes@trilce.com","https://www.trilce.edu.pe/academia",
                "ic_trilce","Alumnos mejor preparados"));
        companies.add(new Company(6,"Academia","Pamer", "Av. Nicolas Ayllon 52",
                "32148965","informes@academiaspamer.com","http://www.pamer.edu.pe/corporacion/",
                "ic_pamer","El futuro está en nuestras manos"));
        companies.add(new Company(7,"Universidad","UPC", "Monterrico Av. 125",
                "984521365","informes@upc.com","https://www.upc.edu.pe/",
                "ic_upc","Exigete, Innova, UPC"));
        companies.add(new Company(8,"Universidad","PUCP", "Av. Guardia Civil 14",
                "54210254","informes@pucp.com","https://www.pucp.edu.pe/",
                "ic_pucp","Somos PUCP, seámoslo siempre"));
        companies.add(new Company(9,"Ropa","Forever 21", "Jockey Plaza - La Molina",
                "987215364","informes@forever21.com","https://www.forever21.com/",
                "ic_forever","The best on the world"));
        companies.add(new Company(10,"Ropa","H & M", "La Rambla - San Borja",
                "012242625","informes@hm.com","https://www.hm.com/pe/",
                "ic_hm","H&M es una cadena sueca de tiendas de ropa, complementos y cosmética " +
                "con establecimientos en Europa, Oriente Próximo, África, Asia y América." +
                " Cuenta con 4700 tiendas propias repartidas en 69 países Asimismo," +
                " vende ropa por catálogo y a través de Internet en 44 países"));
    }

    public static List<Company> getCompanies() {

        return companies;
    }

    public static Company getCompany(Integer id){

        for(Company company: companies){
            if(company.getId().equals(id)){
                return company;
            }
        }

        return null;
    }

    public static List<Company> BuscarCategoria(String categoria){

        List<Company> lista=new ArrayList();

        for(Company company: companies){
            if (company.getCategory().equals(categoria)){
                lista.add(company);
            }
        }
        return lista;
    }
}
