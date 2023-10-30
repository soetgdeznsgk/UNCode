package com.example;

public class MinHeap<T extends Comparable<T>> implements Heap<T>{
    Object[] items;
    int size;
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.size ; i++){
            sb.append(((T) items[i]).toString() + " ");
        }
        return sb.toString();
    }

    @Override
    public T extract() {
        if (size == 0) throw new IndexOutOfBoundsException("Heap vacío");
        T menor = (T) this.items[0];
        
        this.items[0] = this.items[size - 1];
        this.siftDown(0);
        size--;
        return menor;
    }

    @Override
    public T get() {
        if (this.size == 0) return null;
        return (T) this.items[0];
    }

    @Override
    public void add(T element) {
        this.items[size] = element;
        siftUp(size);
        size++;
    }

    @Override
    public void update(int i, T element){
        if (i >= this.size) throw new IndexOutOfBoundsException("huevon");
        
        this.items[i] = element;
        if (((T) items[(i - 1)/ 2]).compareTo(element) > 0) siftUp(i);
        else siftDown(i);
        // items[i] = element, if (items[i -1/2] > items[i]) siftUp(i) else siftDown(i);
    
    }

    @Override
    public T remove(int i) {
        if (i >= this.size) return null;
        this.update(i, (T) this.items[0]);
        T temp = (T) this.extract();
        return temp;

    }
    
    public MinHeap(int capacidad){
        this.items = new Object[capacidad];
        this.size = 0;
    }

    public void siftUp(int i){
        if (((T) this.items[i]).compareTo((T) this.items[(i - 1)/ 2]) < 0){
            T temp = (T) this.items[(i - 1) / 2];
            this.items[(i - 1)/ 2] = this.items[i];
            this.items[i] = temp;
            siftUp((i - 1) /2);
        }
    }

    public void siftDown(int i){
        int indice = i;
        if (this.size <= 2 * i + 1) return; // vemos que no sea una hoja
        T hijoleft = (T) this.items[indice * 2 + 1];
        T hijoright = null;

        if (this.size <= 2 * i + 2){
            hijoright = (T) this.items[indice * 2 + 2];

            if(hijoleft.compareTo(hijoright) < 0){ // cambiar
                T temp = (T) this.items[2 * i + 1];
                this.items[2 * i + 1] = this.items[i];
                this.items[i] = temp;
                siftDown(2 * i + 1);
            } 
            //si el hijo izq es menor que el hijo der
            else if(hijoright.compareTo(hijoleft) <= 0){
                T temp = (T) this.items[2 * i + 2];
                this.items[2 * i + 2] = this.items[i];
                this.items[i] = temp;
                siftDown(2 * i + 2);
            }
        }
        
        else {
            T temp = (T) this.items[2 * i + 1];
            this.items[2 * i + 1] = this.items[i];
            this.items[i] = temp;
            siftDown(2 * i + 1);
        }
    }
}


/*  Como instruir a un arreglo en el camino jedi

    sift down
    1. Verificicamos si x es mayor que alguno de sus hijos en caso de tenerlos.
        x       
       / \
      a   b 
     / \ / 
    o   oo  
    
        a       2. En caso de que x sea mayor que uno de sus hijos se intercambia su valor con el menor de ellos.
       / \         En este ejemplo a<b
      x   b     3. Se repite xd
     / \ / \
    o   oo  o

*/