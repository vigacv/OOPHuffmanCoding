class Huffman(var valores: IntArray){
    //var si cambia de valor
    var listaFrecuencias: ListaF? = null
    var arbolH: ArbolHF? = null
    init{
        ordenarListaValores()
        this.listaFrecuencias = ListaF(this.valores)
        this.arbolH = ArbolHF(this.listaFrecuencias!!)
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

open class Nodo(val frecuencia: Int, val valor: Int){
    //open es que se puede heredar
}

class NodoF(frecuencia: Int, valor: Int): Nodo(frecuencia, valor){
    var sigtNodo: NodoF? = null
    //"?" significa que la variable puede ser null
}

class ListaF(val valores: IntArray){
    var primerNodoF: NodoF? = null;
    var cant: Int = 0
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
                this.cant++
            }
            if(i == this.valores.size-1){
                var nuevoNodo = NodoF(frec,valor)
                nodoPrevio?.sigtNodo = nuevoNodo
                this.cant++
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

class NodoArbol(frecuencia: Int, valor: Int, var subArbolIzq: NodoArbol? = null,
                var subArbolDer: NodoArbol? = null) : Nodo(frecuencia, valor)

class ArbolHF(val listaFrec: ListaF){
    var listaArboles = ArrayList<NodoArbol?>()
    var raizArbol: NodoArbol? = null
    //crea un array de tipo NodoARbol del tamaño de la lista de frec e inicializa todos los valores en null
    init{
        crearListaArboles()
    }
    fun crearListaArboles(){
        var pNodo = listaFrec.primerNodoF
        for(i in 0..this.listaFrec.cant-1){
            var newArbol = pNodo?.let{NodoArbol(it.frecuencia,it.valor)}
            //let ejecuta todo el bloque y lo hace solo si pNodo es diferende de null
            listaArboles.add(newArbol)
            pNodo = pNodo?.sigtNodo
        }
    }
    fun mostrarListaArboles(){
        for(a in this.listaArboles){
            println("${a?.frecuencia}:${a?.valor}")
        }
    }
    fun ordenarListaArboles(){
        for(i in 0..this.listaArboles.size-2){
            for(j in i+1..this.listaArboles.size-1){
                if(this.listaArboles[j]?.frecuencia!! < this.listaArboles[i]?.frecuencia!!){
                    var temp = this.listaArboles[i]
                    this.listaArboles[i] = this.listaArboles[j]
                    this.listaArboles[j] = temp
                }
            }
        }
    }
    fun generarArbolHuffman() {
        while(this.listaArboles.size != 1){
            ordenarListaArboles()
            var suma = this.listaArboles[0]?.frecuencia!! + this.listaArboles[1]?.frecuencia!!
            var newArbol = NodoArbol(suma,0,this.listaArboles[0], this.listaArboles[1])
            println()
            mostrarListaArboles()
            println(suma)
            println()
            this.listaArboles.removeAt(0); this.listaArboles.removeAt(0)
            this.listaArboles.add(newArbol)
        }
        this.raizArbol = this.listaArboles[0]
    }
}

fun main(){
    val vals = intArrayOf(1,2,2,1,3,4,2,1,2,3); //Se usa val porque no cambiara
    val hf = Huffman(vals.copyOf()) //si no se usa copy modifica a vals
    for(v in vals) print("$v ")
    println()
    for(v in hf.valores) print("$v ")
    println("Lista de frecuencias: ")
    hf.mostrarListaFrecuencias()
    println("Tamaño de la lista de frecuencias: ${hf.listaFrecuencias?.cant}")
    println("Lista de arboles:")
    hf.arbolH?.mostrarListaArboles()
    println()
    hf.generarArbolHuffman()
    println("Raiz del arbol de Huffman: ${hf.arbolH?.raizArbol?.frecuencia}:${hf.arbolH?.raizArbol?.valor}")
    println(hf.arbolH?.raizArbol?.subArbolDer?.subArbolDer?.subArbolIzq?.valor)
}