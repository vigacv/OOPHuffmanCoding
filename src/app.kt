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
    fun mostrarListaFrecuencias(){
        this.listaFrecuencias?.mostrarListaF()
    }
    fun generarArbolHuffman(){
        this.arbolH?.generarArbolHuffman()
    }
    fun codificar(x: Int){

    }
    fun decodificar(cod: Int){

    }
    fun ordenarListaValores(){
        for(i in 0..this.valores.size-2){
            for(j in i+1..this.valores.size-1){
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
    init{
        generarListaF()
    }
    fun generarListaF(){
        var frec = 0
        var valor = this.valores[0]
        var nodoPrevio: NodoF? = null
        for(i in 0..this.valores.size-1){
            if(this.valores[i] == valor){
                frec++
            }else{
                var nuevoNodo = NodoF(frec,valor)
                valor = this.valores[i]
                frec = 1
                if(this.primerNodoF == null){
                    this.primerNodoF = nuevoNodo
                }else{
                    nodoPrevio?.sigtNodo = nuevoNodo
                }
                nodoPrevio = nuevoNodo
            }
            if(i == this.valores.size-1){
                var nuevoNodo = NodoF(frec,valor)
                nodoPrevio?.sigtNodo = nuevoNodo
            }
        }
    }
    fun mostrarListaF(){
        var pNodo = this.primerNodoF
        while(pNodo != null){
            println("${pNodo.frecuencia}:${pNodo.valor}")
            pNodo = pNodo.sigtNodo
        }
    }
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
    val vals = intArrayOf(1,2,2,1,3,4,2,1,2,3); //Se usa val porque no cambiara
    val hf = Huffman(vals.copyOf()) //si no se usa copy modifica a vals
    for(v in vals) print("$v ")
    println()
    for(v in hf.valores) print("$v ")
    println()
    hf.crearListaFrecuencias()
    hf.mostrarListaFrecuencias()
}