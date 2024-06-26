## Integrantes
Francisco Infanti 110822

Franco Alani 111147

## Diagrama de Clases UML

<div>
<img width="2651" src="doc/Diagrama.png" alt="Diagrama">
</div>

La Clase `Juego` tiene una composición con las Clases `Mapa`, `Puntaje`, `Jugador`, `RobotX1`, `RobotX2` y `Explosion`, porque el *Objeto* definido 
en esta se guarda, en uno de sus atributos, una referencia a una instancia de cada una de estas Clases. Entonces esto nos deja con la posibilidad de 
que la relación sea una asociación, agregación o composición. 
Dado que las instancias de esas Clases se instancian dentro del *Objeto* en sí, la relación es una composición, pues todos tienen el mismo ciclo 
de vida. Ya que cuando instanciamos un `Juego`, automáticamente estamos instanciando las demas Clases. Y cuando se libera la memoria de la instancia 
de `Juego`, también se libera la memoria de las instancias de las demas Clases. Podemos pensar al `Juego` como un todo y que sus partes son `Mapa`,
`Puntaje`, `Jugador`, `RobotX1`, `RobotX2` y `Explosion`.

A su vez, `Juego` tiene una dependencia con `Coordenadas`, pues el *Objeto* definido en `Juego`, no se guarda en sus atributos una instancia a esta
Clase. Y en el caso de que use una, la recibe por parámetro en un método o la instancia dentro de un método.

Tanto `Jugador` como `Enemigo` heredan de `Personaje`. Luego `Personaje` es una Superclase y ademas `Jugador` y `Enemigo` son un `Personaje`. Entonces 
estos tienen una relación generalización con `Personaje`. 
A su vez, `RobotX1`, `RobotX2` y `Explosion` heredan de `Enemigo`, entonces la Subclase `Enemigo` resulta ser una Superclase de estas Clases. 
Luego estas tres Clases son un `Enemigo` y un `Personaje`, pero decimos que estas tienen una relación de generalización con `Enemigo`.

Veamos que el *Objeto* definido en la Clase `Personaje` guarda, en uno de sus atributos, una referencia a una instancia de `Coordenadas`. Luego la 
relación puede que sea una composición, agregación o asociación. Ahora, dado que se recibe la instancia de `Coordenadas` y no se la instancia en 
el *Objeto* en sí, podemos descartar que la relación sea una composición, pues no van a tener el mismo ciclo de vida. Por lo tanto la relación puede 
ser una agregación o una asociación. Para nosotros es una agregación, pues el *Objeto* definido `Personaje`, depende de las coordenadas y tienen 
un ciclo de vida distinto. Podemos verlo como que el `Personaje` está conformado por una `Coordenada`.

Por último veamos que el *Objeto* definido en la Clase `Mapa`, también como `Personaje`, guarda en uno de sus atributos una referencia a una instancia 
de `Coordenadas`. Nuevamente la relación puede que sea una composición, agregación o asociación. Ahora, también como ocurrió en `Personaje`, la 
instancia de `Coordenadas` no se instancia en la en el *Objeto* en sí, sino que se recibe por parámetro. Luego descartamos que sea una composición. 
Para nosotros, esta relación también es una agregación, pues el `Mapa` depende de las `Coordenadas` y además tienen distinto ciclo de vida. También 
podemos hacer una analogía como antes y ver que el `Mapa` está conformado por `Coordenadas`.

 

