package ejercicio4uncode;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * El problema dicta que hay 1 estudiante que está infectado de SARS, y dada la cantidad de estudiantes, la cantidad de grupos y la formación de cada grupo. ¿Cuántos estudiantes son sospechosxs de portar SARS?
 * Un estudiante es sospechoso cuando comparte grupo con alguien de quien se sospecha.
 * 
 * El estudiante infectado  será siempre el estudiante cuyo "nombre" es 0
 */

class Estudiante{
        int valor;
        Boolean contagiado;
        LinkedList<LinkedList<Estudiante>> grupos;

        public Estudiante(int valor) {
            this.valor = valor;
            this.grupos = new LinkedList<LinkedList<Estudiante>>();
            this.contagiado = false;
        }
    }



public class Main {

    static Estudiante[] estudiantes;
    static LinkedList<LinkedList<Estudiante>> gruposGlobales;
    static int cuenta = 1;

    /*
     * Para simular el contagio, cada Estudiante tiene una lista de todas los grupos a los que pertenece, 
     * Con ésto, se parsea a través de cada estudiante con quien comparte y si ese estudiante NO está bajo sospecha, se cataloga como sospechosx, se aumenta la cuenta de gente sospechosa y se repite el proceso en su círculo
     */

    static void contagio(int indice){
        for (LinkedList<Estudiante> grupo : estudiantes[indice].grupos){
            for (Estudiante e: grupo){
                if (!e.contagiado){
                    e.contagiado = true;
                    cuenta++;
                    contagio(e.valor);
                }
            }
        }
    }

    /*
     * La lógica es medio complicada xD
     * 
     * Sólo me parece importante notar que está hecha así para que reciba los datos de la siguiente manera:
     * 
     * #Estudiantes #Grupos
     * #EstudiantesGrupo1 estudiante1Grupo1 estudiante2Grupo1 ...
     * #EstudiantesGrupo2 ...
     * ...
     * 0 0
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        
        while (true){
            int cEstudiantes = sc.nextInt();
            int cGrupos = sc.nextInt();
            
            if (cEstudiantes == 0 && cGrupos == 0) break;
            sc.nextLine();

            estudiantes = new Estudiante[cEstudiantes];
            estudiantes[0] = new Estudiante(0);
            gruposGlobales = new LinkedList<LinkedList<Estudiante>>();

            for(int i = 0; i < cGrupos; i++){
                gruposGlobales.add(new LinkedList<Estudiante>());

                int cEstPorGru = sc.nextInt();
                for (int j = 0; j < cEstPorGru; j++){
                    int valor = sc.nextInt();

                    if (estudiantes[valor] == null){
                        estudiantes[valor] = new Estudiante(valor);
                    }

                    gruposGlobales.get(i).add(estudiantes[valor]);
                    estudiantes[valor].grupos.add(gruposGlobales.get(i));
                    
                }
                sc.nextLine();
            }
            estudiantes[0].contagiado = true;
            contagio(0);
            System.out.println("estudiantes infectadxs: " + cuenta);
            cuenta = 1;
        }

        sc.close();
    }
}