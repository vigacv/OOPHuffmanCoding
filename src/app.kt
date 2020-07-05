class Huffman(var valores: IntArray){
    //var si cambia de valor
    var listaFrecuencias: ListaF? = null
    var arbolH: ArbolHF? = ArbolHF(valores)
    init{
        ordenarListaValores()
    }
    fun crearListaFrecuencias(){
        this.listaFrecuencias = ListaF(this.valores)
    }
    fun generarArbolHuffman(){
        this.arbolH?.generarArbolHuffman()
    }
    fun codificar(x: Int){

    }
    fun decodificar(cod: Int){

    }
    fun ordenarListaValores(){
        for(i in 1..valores.size-1){
            for(j in 1..valores.size){
                if(this.valores[i]>this.valores[j]){
                    var temp = this.valores[i]
                    this.valores[i] = this.valores[j]
                    this.valores[j] = temp
                }
            }
        }
    }
}

open class Nodo(val frecuencia: Int, val valor: Int?){
    //open es que se puede heredar

}

class NodoF(frecuencia: Int, valor: Int): Nodo(frecuencia, valor){
    var sigtNodo: NodoF? = null
    //"?" significa que la variable puede ser null
}

class ListaF(val valores: IntArray){
    var primerNodoF: NodoF? = null;
}

class NodoArbol(frecuencia: Int, valor: Int) : Nodo(frecuencia, valor){
    var subArbolIzq: NodoArbol? = null
    var subArbolDer: NodoArbol? = null
}

class ArbolHF(val valores: IntArray){
    fun generarArbolHuffman() {
        TODO("Not yet implemented")
    }

    var raizArbol: NodoArbol? = null
}

fun main(){
    val valores = intArrayOf(1,2,2,1,3,4,2,1,2,3); //Se usa val porque no cambiara
    val hc = Huffman(valores)

}