package co.edu.poli.gamification.poliplaygami.Juegos;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import co.edu.poli.gamification.poliplaygami.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;

public class JuegoTrivia extends AppCompatActivity{

    //---------------------------------------------------------------------------
    //
    // Falta poner los contadores de tiempo de juego y conexion
    //
    //---------------------------------------------------------------------------

    private String [] enunciadosPreg ={

            //1
            "Un administrador coordina y aprovecha los recursos de la organizacion y las personas, para alcanzar los objetivos de la empresa con eficacia y eficiencia. Lo hace basicamente a traves de cuatro funciones basicas; planear, organizar, dirgir y controlar. Una de estas funciones se refiere especialmente entender las tareas que deben ser realizadas, quien las debe realizar, como seran coordinadas y controladas y cuales son las relaciones que se deben establecer para el cumplimiento de ellas. Enunciado: De acuerdo con el contexto citado identifique cual es la función administrativa descrita:",
//2
            "En cada trabajo gerencial existe un alcance o rango de responsabilidad que está definido en términos del enfoque de sus deberes y de las relaciones de trabajo dentro de la organización, lo cual constituye que en las organizaciones existen determinados niveles gerenciales. Según lo planteado hay administradores que son responsables de establecer las estrategias de diferentes negocios de la empresa y de coordinar sus resultados de manera conjunta. Enunciado: Relacione correctamente el contexto planteado con uno de los siguientes niveles gerenciales:",
//3
            "La función administrativa de organización se define como una estructura técnica de relaciones entre niveles jerárquicos, funciones, actividades, elementos materiales y su capital humano para alcanzar su máxima eficiencia en el marco de los objetivos corporativos. Para alcanzar el propósito que se propone la función organización se crean unas interrelaciones e interacciones que conforman un proceso integral a través de tres elementos claramente establecidos. Enunciado: Según el contexto enunciado identifique correctamente los elementos de la función organización entre las siguientes opciones de respuesta:",
//4
            "La matriz DOFA es una herramienta de análisis estratégico de las diferentes situaciones de la organización su manejo para determinar las estrategias FO – DA – FA – DO consiste en relacionar las fortalezas, amenazas, oportunidades y debilidades de la organización. El propósito es usar las fortalezas para aprovechar las oportunidades, usar las fortalezas para evitar las amenazas, vencer las debilidades aprovechando las oportunidades y reducir al mínimo las debilidades y evitar las amenazas, a través de la formulación de estrategias. Enunciado: De acuerdo al contexto descrito identifique entre las siguientes estrategias, cuales son el resultado de tener un buen aprovechamiento de las oportunidades que ofrece el entorno y las fuentes de ventajas propias, a fin de generar estrategias de crecimiento que generen una postura de expansión encauzada a logro de sus objetivos corporativos.",
//5
            "El administrador que planea utiliza herramientas y técnicas que le ayudan a tomar decisiones. El propósito de estas es identificar situaciones y proyectarse para adoptar efectivamente estrategias que tengan un impacto importante y reconozcan el entorno en el que estas se desenvuelven. Una de estas herramientas describe situaciones futuras en la que actuara una empresa si la aplicamos a este contexto y a los hechos que forman parte de este mismo. Enunciado: De acuerdo al contexto identifique cual es la herramienta de planeación que se describe:",
//6
            "Dos hechos históricos dieron origen a una nueva era como consecuencia del proceso de industrialización, ellos fueron el crecimiento apresurado y desorganizado de las organizaciones y la ineficiencia de las organizaciones. En consecuencia de esta situación se desarrollaron varias teorías de la administración una de estas está orientada a optimizar el trabajo dando respuestas a los problemas de eficiencia con el objeto de aumentar la productividad mejorando el desempeño de los trabajadores, oriento su estudio en la disminución de tiempos y movimientos aplicando el método científico, el objetivo estaba centrado en el bienestar mutuo lo cual significaba que si a la empresa le iba bien a trabajador también le iba bien. Enunciado: Según el contexto citado identifique la teoría de administración allí descrita:",
//7
            "Como consecuencia del proceso de industrialización se dieron dos hechos que dieron paso a una nueva época de la administración, ellos fueron el crecimiento apresurado y desorganizado de las organizaciones y la ineficiencia de las organizaciones. En consecuencia de esta situación se desarrollaron varias teorías de la administración. Una de estas teorías de administración plantea que lo más importante es la gerencia en las organizaciones de ahí la necesidad de estudiarla, analizarla y estructurarla. Definió las funciones del administrador, dando origen al proceso administrativo, propuso que planear, organizar, coordinar, dirigir y controlar son funciones básicas para todas y cada una de las áreas de la empresa, para su desarrollo y para el desempeño de cualquier tipoi de trabajo. Enunciado: Según el contexto citado identifique la escuela de administración allí descrita:",
//8
            "La teoría de la calidad total plantea que la calidad es la totalidad de características de una entidad que le confiere la capacidad para satisfacer necesidades explicitas e implícitas, sin embargo se llama calidad total porque porque analiza y conoce todos los aspectos de la organización y logra comprometer a todos y a cada uno de los miembros de la misma. El concepto de calidad buscaba en un principio simplemente corregir los errores cometidos, no obstante el concepto de calidad total busca hacer todo perfecto desde la primera etapa. Para ayudar a los gerentes de una empresa existe una herramienta de mejora continua llamada ciclo de calidad PHVA el cual basa su ciclo en 4 pasos; Planear, Hacer, Verificar y Actuar. Uno de estos pasos del ciclo se refiere a la trasformación de los procesos actuales mejorando los productos. Enunciado: Infiera de los siguientes pasos del ciclo PHVA cuál es el descrito en el contexto:",
            //--------------------------------------------------------
            //1
            "Se habla de que toda acción que se convierta en un plan debe ser administrada, es decir, monitoreada mediante indicadores, que, como su nombre lo dice, indican cómo vamos en el proceso, para realizar ajustes que permitan su adaptación. Este aspecto está estrechamente ligada a la función de planeación, por lo que al administrar el proceso se cotejan esta función para medir así el éxito empresarial. Al requerir reformular los planes en función de los cambios del entorno, se obliga a realizar ajustes para redireccionarlos; al no tener que reformular, se confirma que no se hace seguimiento. Luego en este apartado hacemos referencia a que en las organizaciones vamos a:Se habla de que toda acción que se convierta en un plan debe ser administrada, es decir, monitoreada mediante indicadores, que, como su nombre lo dice, indican cómo vamos en el proceso, para realizar ajustes que permitan su adaptación. Este aspecto está estrechamente ligada a la función de planeación, por lo que al administrar el proceso se cotejan esta función para medir así el éxito empresarial. Al requerir reformular los planes en función de los cambios del entorno, se obliga a realizar ajustes para redireccionarlos; al no tener que reformular, se confirma que no se hace seguimiento. Luego en este apartado hacemos referencia a que en las organizaciones vamos a:",
            //2
            "La   selección   de   personas idóneas para ocupar una posición laboral se conoce como un   proceso   complejo   de   evaluación   de   los   candidatos de modo tal que permita    encontrar   quién   es   el   mejor   calificado   para   ocupar   un   cargo   determinado al interior de la organización, es entonces cuando este instrumento se aplica para  establecer si el candidato posee conocimientos  y competencias necesarias para el cargo a ocupar  así mismo es una oportunidad para dar a conocer no solo la empresa sino las particularidades del cargo, este instrumento se conoce como",
//3
            "Esta herramienta permite gestionar la planeación y actividades de control de doble entrada, de modo tal que la organización desagregue sus propias actividades, así como los tiempos de ejecución necesarios para desarrollarlas, y las responsabilidades de estas.  La anterior herramienta se conoce como:",
//4
            "Si bien es cierto las organizaciones no son iguales, todas tienen unos principios generales que las hacen comunes entre sí, uno de dichos principios se encarga de Agrupar las actividades de acuerdo con el grado de afinidad entre ellas contemplando (analogía y complementariedad). Dicho principio se conoce como:",
//5
            "La estructura organizacional que permite agrupar actividades y personas con base en el mercado objetivo es denominada como:",
//6
            "La señora Blanca gerente de la empresa Pinturas de Colores, instauró la política administrativa de “libro abierto” y con base en la misma decidió hacer que sus principales clientes supieran con precisión cuales eran los costos de la empresa, pues ya estaba cansada de oírlos quejarse frecuentemente por los elevados precios de la compañía. Como su política dice que no hay nada que esconder, instruyó a los vendedores para que informaran a los clientes de estos costos. Además, la señora Blanca considera que realizando seminarios a los clientes se puede ser un valor agregado, pues además de venderles se les puede ilustrar sobre diferentes aspectos que les proporcione beneficios adicionales.  Con lo expuesto Anteriormente, se puede inferir que la gerente está desarrollando roles:",
//7
            "CIC La señora Azucena Linda, presidente de la empresa Flores Silvestres, está analizando los resultados de la empresa y encuentra que la empresa no presenta una evolución ni el crecimiento que tienen en mente los accionistas; con base en esta premisa ha decidido conformar un comité con un amplio número de personas que representan todas las áreas de la organización, con el fin de estudiar un plan que cumpla con las exigencias de crecimiento y rentabilidad propuesta por los dueños de la empresa. Con lo expuesto anteriormente, se puede inferir que la señora Rosa está realizando un proceso de:",
//8
            "La señora Robledo, dueña de la empresa el Maderas el Roble Florecido, después de varios años de trabajo, ha decidido que la compañía debe pasar de ser una organización movida por las áreas funcionales a ser una impulsada por el mercadeo o sea una empresa orientada al cliente y determinó el enfoque empresarial en función de las nuevas oportunidades de mercadeo; por tal motivo creó divisiones de acuerdo con los tipos de clientes y direcciones por las principales áreas geográficas. De acuerdo con este planteamiento se puede inferir que la señora Robledo, redireccionó su empresa mediante una departamentalización mixta:",
//9
            "El señor Marco Cuadrado, lleva 5 años con su negocio, en este tiempo ha logrado tener una expansión importante y su negocio ha crecido considerablemente, por tal razón necesita constantemente contratar nuevo personal que deben realizar diferentes actividades como resultado del continuo crecimiento, sin embargo el reclutamiento de los nuevos funcionarios se ha convertido en un dolor de cabeza, pues no siempre se contratan las personas con el perfil adecuado y se requiere hacer reemplazos de personal continuamente, como el señor Márquez no sabe cómo realizar esta actividad con eficiencia y eficacia, lo ha contratado a usted para que le diga y lo oriente al respecto, dado el contexto expuesto, usted le aconseja realizar:",
//10
            "El señor Roca Fuerte gerente de la empresa Mármoles y Piedras, está muy interesado en evaluar a todos sus empleados para tener una visión real del desempeño de cada uno de sus funcionarios, para tal efecto ha decidido que necesita conocer los diferentes puntos de vista y perspectivas que vienen desde sus jefes, compañeros, subordinados, colegas y del mismo trabajador. Con lo expuesto en el contexto se puede deducir que el Sr. Roca Fuerte está utilizando:",
//11
            "La empresa Construcol compite en el sector de ingeniería y construcción, para desarrollar un nuevo proyecto conformó un equipo de alto rendimiento compuesto por profesionales de diferentes áreas. Durante 16 meses el equipo trabajo en el desarrollo del proyecto, una vez cumplidos los objetivos planeados; el equipo se disolvió y cada uno de los integrantes volvió a desempeñar sus funciones en el área correspondiente.  La estructura conformada temporalmente para el desarrollo del proyecto se conoce como:",
//12
            "Las grandes superficies como Éxito, Olímpica, Jumbo; para el desarrollo de las diferentes actividades del negocio organiza sus actividades y funcionarios de acuerdo con el siguiente tipo de departamentalización:",
//13
            "El conjunto de formas de actuar, de sentir y de pensar que se comparten entre los miembros de la organización y que permiten identificar la empresa ante los clientes, proveedores y todos los que conocen de su existencia. Sus normas, sus valores, sus hábitos son en definitiva lo que es la empresa.  El anterior concepto, corresponde en la función de integración de personal a:",
//14
            "La empresa ALFA S.A, fundada en 1966 ha desarrollado su actividad en el sector de las confecciones, inicialmente su crecimiento en ventas fue representativo; en los últimos tres años ha experimentado dificultades para mantener este desempeño.  Los miembros de la alta gerencia han decidido subcontratar ciertas actividades que ha venido desarrollando la empresa y que no son parte de su foco de negocio; actividades como Seguridad de las instalaciones, servicios generales, mantenimiento de las máquinas.  Esta enfoque dministrativo se identifica como:",
//15
            "La revolución Industrial transformó el concepto de trabajo y tuvo una serie de consecuencias que impactaron en todos los campos: Economía, sociedad, agricultura, Industria, etc.  Los países industrializados (Inglaterra, Francia, Alemania, Holanda, Bélgica, Italia) desarrollaron su economía basados en la propiedad de la máquina, mano de obra disponible, capital disponible y Mercados para vender su producción, el único elemento que no poseían eran las materias primas que las encontraron en África y América de donde comenzaron a explotar estos recursos sin transferir la tecnología a los países de donde obtenían los insumos, esta acción se conoce como:",
//16
            "El gerente general de Industrias La Martina, Mario Quintanilla, ha decidido asignar un presupuesto al Departamento Comercial de su empresa recién creada, y otro presupuesto para la compra de insumos necesarios en la producción de zapatos, esta asignación de presupuestos, desde el punto de vista administrativo se puede clasificar como una función de",
            //Antiguas--------
            //
            "El éxito empresarial  depende de la habilidad para planear efectivamente y asegurar su efectividad  en alineación con e control.  Esto se explica porque la planeación",
            //
            "Jóvenes emprendedores han diseñado unos medios de transporte para la prestación del servicio para salir de la isla aprovechando su entorno para tomar decisiones estratégicas. El mayor reto que deberá enfrentar próximamente es:",
            //
            "En una situación desconocida, es importante tomar decisiones para actuar, deberán enfrentar rápidamente un proceso de selección de personas que  permita  elegir los mejores para un buen desempeño. Una combinación para prever lo anterior sería:",
            //
            "Un modelo de negocios es eficiente, pero no eficaz cuando:",
            //
            "Los administradores no solo aplican el proceso administrativo, sino que también desempeñan roles o papeles que según Henry Mintzberg los podemos categorizar en:",
            //Trivia 2
            "Un buen administrador coordina y aprovecha los recursos de la organización y las personas, para alcanzar los objetivos con eficacia y eficiencia. Obtiene resultados a través del proceso administrativo y de su equipo de trabajo. De acuerdo con lo anterior estas son funciones principales de:",
            //
            "Un grupo de estudiantes naufragan en una isla y deben crear un modelo de negocios novedoso orientado a la creación de un medio de transporte que los saque cuanto antes del lugar.  Actualmente los equipos se encuentran adelantando un diagnóstico, con el fin de establecer su nueva línea de transporte sostenible financieramente. Para evaluar el impacto en su nuevo proyecto, sería conveniente iniciar el diagnostico con un:",
            //
            "Unos jóvenes emprendedores acaban de naufragar en una isla en la que deben salir antes de 2 meses. Deben hacer algo, pero no saben cómo, iniciaran su negocio de manera empírica, deben actuar pronto y hacer realidad su idea de negocio condicionado por la falta de dinero. De acuerdo con la historia anterior, ¿Para que los equipos no pierdan tiempo y dinero que debería hacer?  De acuerdo a lo anterior, deberán nombrar oportunamente un líder que los oriente en el qué y cómo hacer y qué espera de ellos, generando al equipo un beneficio porque:",
            //
            "Unos jóvenes emprendedores acaban de naufragar en una isla en la que deben salir antes de 2 meses.  ¡Cuentan con un equipo de trabajo para sumir y lograr el reto!! \n" +
                    "\n" +
            "Los equipos deben hacer algo, pero no saben cómo, iniciaran su negocio de una manera empírica, deben actuar pronto y hacer realidad su idea de negocio condicionado por la falta de dinero. De acuerdo con la historia anterior, ¿Para que los equipos no pierdan tiempo y dinero que debería hacer?",
            //
            "Un equipo náufrago está iniciando su empresa de construcción de un medio de transporte para salir de la isla en un periodo corto de tiempo. Recientemente ganó unos retos para su  construcción, sin embargo, no cuenta con todo el personal, porque están pensando en una alianza para servicios especializados e independientes. Para lo que desea hacer el equipo la estructura por procesos es la más ventajosa ¿por qué?",
            //
            "Unos náufragos desean sacar adelante el proyecto profesional de sus sueños, un modelo de negocios que les permita salir de la isla, la cual desde hace unas semanas viene presentando problemas financieros, tienen como ideas vender excedentes de materia prima u ofrecer  el servicio de transporte. De acuerdo con la historia anterior, si los náufragos ofrecen puestos a otras personas y con el dinero pagan las deudas y equipo esta:",
            //
            "Unos náufragos desean sacar adelante el proyecto profesional de sus sueños, un modelo de negocios que les permita salir de la isla, la cual desde hace unas semanas viene presentando problemas financieros, tienen como ideas vender excedentes de materia prima u ofrecer el servicio de transporte. De acuerdo con la historia anterior, ¿qué es lo primero que deben hacer los náufragos para tomar una buena decisión?",
            //
            "El líder unos jóvenes náufragos se ha sentido frustrado, desde hace 3 semanas vienen desarrollando un proyecto para ayudarlos a salir de la isla y a otros mediante una reducción de costos fijos, generando ahorro en insumos; algunos no convencidos la consideran innecesaria.  El líder del equipo que se ve como un Open Mind tiene una idea Mcgregoriana del comportamiento de sus compañeros de equipo porque:",
            //
            "Un cliente requiere solucionar la problemática presentada en él lugar del naufrago, reúne a expertos para crear opciones. De acuerdo con lo anterior, ¿cuál sería el nombre de las personas que se reúnen y cual su enfoque para encontrar la solución? ",
            //
            "El líder unos jóvenes náufragos se ha sentido frustrado, desde hace 3 semanas vienen desarrollando un proyecto para ayudarlos a salir de la isla y a otros mediante una reducción de costos fijos, generando ahorro en insumos; algunos no convencidos la consideran innecesaria. Frente a la negativa, el líder del equipo está pensando en abandonar el proyecto ya que ve que esta no le hace honor a su nombre “Open Mind”",
            //
            "Unos jóvenes emprendedores acaban de naufragar en una isla en la que deben salir antes de 2 meses.  ¡Cuentan con un equipo de trabajo para sumir y lograr el reto!! \n" +
                    "\n" +
                    "La isla está dotada de 7 zonas para abastecer de recursos para la sobrevivencia y crear un medio de transporte eficiente que les permita alcanzar el objetivo, sin embargo, frente a la situación y en medio del asombro las personas manifiestan no tener claro que deben hacer al no tener mucha experiencia. \n" +
                    "\n" +
                    "Los equipos de trabajo saben que debe hacer algo, pero no sabe cómo, ya que iniciaran su negocio de una manera muy empírica y lo que saben lo ha venido aprendiendo sobre la marcha, saben que si no actúan pronto las personas el tiempo se les agotara por el tiempo que les tomara hacer realidad su idea de negocio y no podrá perder mucho dinero. \n" +
                    "\n" +
                    "Enunciado: \n" +
                    "\n" +
                    "¿De acuerdo con la historia anterior, Para que los equipos no pierdan tiempo y dinero que debería hacer?",
            //
            "Unos jóvenes emprendedores acaban de naufragar en una isla en la que deben salir antes de 2 meses.  ¡Cuentan con un equipo de trabajo para sumir y lograr el reto!! \n" +
                    "\n" +
                    "La isla está dotada de 7 zonas para abastecer de recursos para la sobrevivencia y crear un medio de transporte eficiente que les permita alcanzar el objetivo, sin embargo, frente a la situación y en medio del asombro las personas manifiestan no tener claro que deben hacer al no tener mucha experiencia. \n" +
                    "\n" +
                    "Los equipos de trabajo saben que debe hacer algo, pero no sabe cómo, ya que iniciaran su negocio de una manera muy empírica y lo que saben lo ha venido aprendiendo sobre la marcha, saben que si no actúan pronto las personas el tiempo se les agotara por el tiempo que les tomara hacer realidad su idea de negocio y no podrá perder mucho dinero. \n" +
                    "\n" +
                    "Enunciado: \n" +
                    "\n" +
                    "De acuerdo con la historia anterior, los equipos deberán nombrar de manera oportuna un líder que especifique a cada integrante del equipo que debe hacer, como lo debe hacer y que espera de ellos, lo que los beneficia porque:",
            //
            "Un grupo de estudiantes naufragan en una isla y deben crear un modelo de negocios orientado a la creación de un medio de transporte que los saque cuanto antes del lugar.   \n" +
                    "\n" +
                    "Sera una organización nueva en ese mercado, que se dedica a la producción y comercialización de medios de transporte, cuenta con 7 zonas para abastecerse y un equipo de personas creativas y emprendedoras.  En la actualidad los equipos se encuentran adelantando un diagnóstico para entrar en un proceso de planeación y organización estratégica, con el fin de establecer su nueva línea de transporte que permitan una alta rotación y genere ventas para lograr una sostenibilidad financiera. \n" +
                    "\n" +
                    "Enunciado: \n" +
                    "\n" +
                    "Para que los equipos inicien con el diagnostico estratégico y tengan la herramienta para evaluar a fondo_principal el impacto para su nuevo proyecto, sería conveniente iniciar el diagnostico con un:",
            //
            "Un buen administrador coordina y aprovecha los recursos de la organización y las personas, para alcanzar los objetivos con eficacia y eficiencia. Obtiene resultados a través de la planeación, organización, dirección, control e integración y el del equipo de trabajo conformado por personas de la organización. \n" +
                    "\n" +
                    "Enunciado: \n" +
                    "\n" +
                    "De acuerdo con el contexto anterior la planeación, organización, dirección, control e integración son las funciones principales de:"
    };

    private String[][] opcionesRespuestas = {
            //1
            {"Planear",
                    "Organizar",
                    "Controlar",
                    "Dirigir"},
//2
            {"Direccion funcional o linea",
                    "Direccion media",
                    "Direcion general",
                    "Ninguna de las anteriores"},
//3
            {"personas, recursos, objetivos",
                    "autonomía, recursos, objetivos",
                    "personas, recursos, identidad",
                    "Ninguna de las anteriores"},
//4
            {"Estrategias FA",
                    "Estrategias DO",
                    "Estrategias FO",
                    "Estrategias DA"},
//5
            {"El diagrama causa - efecto",
                    "El diagrama de Gantt",
                    "La matriz DOFA",
                    "La técnica de escenarios"},
//6
            {"Administración clásica",
                    "Administración científica",
                    "Administración sistémica",
                    "Administración burocrática"},
//7
            {"Administración sistémica",
                    "Administración científica",
                    "Administración clásica",
                    "Ninguna de las anteriores"},
//8
            {"Actuar",
                    "Hacer",
                    "Planear",
                    "Verificar"},
            //--------------------------
            //1
            {"Dirigir","Ejecutar","Planear","Controlar"},
            //2
            {"Pruebas de personalidad",
                    "Entrevistas",
                    "Formularios de aplicación",
                    "Examen o test"},
            //3
            {"Matriz DOFA",
                    "Diagrama de Gantt",
                    "Técnica de escenarios",
                    "Diagrama Causa-efecto"},
//4
            {"Departamentalización",
                    "Funcionalización",
                    "Centralización",
                    "Unidad de Mando"},
//5
            {"Estructura organizacional Matricial",
                    "Estructura organizacional por división",
                    "Estructura organizacional en red",
                    "Estructura organizacional por funciones"},
//6
            {"Interpersonales",
                    "Informativos",
                    "Negociación",
                    "De decisión"},
//7
            {"Estrategia Corporativa",
                    "Estrategia de Negocios",
                    "Estrategia Funcional",
                    "Estrategia Operacional"},
//8
                    {"Por clientes y Geográfica",
                            "Por Procesos y por Clientes",
                            "Matricial y por Productos",
                            "Geográfica y por Productos"},
//9
                    {"Planear e implementar un adecuado proceso de integración de personal.",
                            "Promover a los funcionarios actuales de acuerdo con el conocimiento y experiencia de cada uno.",
                            "Solicitar a los funcionarios actuales que le recomienden personas para los nuevos cargos.",
                            "Incluir en el proceso de planeación el reclutamiento del personal requerido para los nuevos cargos."},
//10
                    {"Evaluación del desempeño 360 grados",
                            "Evaluación en gerencia por objetivos",
                            "Evaluación del desempeño integral",
                            "Evaluación del desempeño planeado"},
//11
                    {"Funcional",
                            "Geográfica",
                            "Matricial",
                            "Producto/ mercado."},
//12
                    {"Geográfica",
                            "Matricial",
                            "Tiempo",
                            "Cliente/producto"},
//13
                    {"Departamentalización.",
                            "Cultura",
                            "Estructura",
                            "Evaluación"},
//14
                    {"Reingeniería",
                            "Outsourcing",
                            "Benchmarking",
                            "Empowerment"},
//15
                    {"Monopolio",
                            "Capitalismo Industrial",
                            "Plusvalía",
                            "Colonialismo Industrial"},
//16
                    {"Planeación, puesto que parte de la función de planeación consiste en asignar presupuestos.",
                            "Organización, ya que la asignación de recursos incluso financieros es propia de esta función",
                            "Dirección, debido a que el gerente general de una empresa requiere presupuestar para dirigir",
                            "Control, pues la función de control se pone en funcionamiento ante el uso de recursos financieros"},

            //
            //ANTIGUAS-----------------------------
            //
            {"Depende de La habilidad de los administradores de desarrollar un plan que reúna y coordine todo el conjunto de tareas que ocurren dentro de ella",
                    "Identifica y diseña las actividades que son necesarias para obtener los objetivos o metas de una organización",
                    "Es un grupo de personas que trabajan juntos en busca de un objetivo común",
                    "Es un proceso en el que los gerentes y los líderes demuestran su capacidad para resolver problemas y convertirlos en oportunidades"},
            //
            {"Competencia creciente en un mercado globalizado.",
                    "Calificación y desarrollo de sus técnicos programadores",
                    "Capacidad financiera para apalancar su crecimiento",
                    "Obsolescencia de sus equipos y recursos tecnológicos."},
            //
            {"La experiencia y las relaciones interpersonales.",
                    "La motivación y la competencia",
                    "Las relaciones interpersonales y el liderazgo",
                    "La autoridad y la competencia."},
            //
            {"La Dirección no tiene una buena comunicación para trasmitir a los integrantes del equipo los objetivos de la planeación.",
            "La Dirección no tiene un alto nivel de negociación con sus proveedores",
            "La Dirección no selecciono el personal adecuado con las competencias y destrezas para el desarrollo de sus actividades",
            "La Dirección no hace el respectivo análisis"},
            //
            {"Vocero, Diseminador, Clarificador",
            "Emprendedor, Manejador   de Crisis, Repartidor de Recursos",
            "Representante, Líder, Enlace",
            "Interpersonales, Informativos, Decisorios"},
        //
            {"El presidente de la junta directiva de la organización",
            "El administrador ",
            "Los accionistas de la empresa",
            "El director de planeación"},
            //
            {"Estudio de mercados",
            "Encuestas y entrevistas",
            "Análisis del entorno",
            "Análisis interno"},
            //
            {"Los equipos   empezarán a vender más porque controlan sus actividades",
            "Los equipos tendrán mayor estabilidad en su trabajo porque están más satisfechos con lo que hacen",
            "Los equipos producirán más y se sentirán más satisfechos",
            "Los equipos se sentirán más satisfechos porque entienden el significado de sus trabajos y tienen control sobre lo que hacen",},
            //
            {"Crear un plan de capacitación",
            "Hacer un mejor reclutamiento y selección",
            "Diseñar bien los cargos que necesita",
            "Especializar el cargo de vendedora"},
            //
            {"Le permite adaptarse a las necesidades locales",
            "Le permite aplicar a los procesos de manufactura con un equipo determinado",
            "Le permite realizar más actividades con menores recursos",
            "Le permite tener grupos de trabajo más cohesionados"},
            //
            {"Estableciendo un objetivo a realizar",
            "Estableciendo una estrategia para solucionar su problema",
            "Tomando una decisión",
            "Implantando una decisión"},
            //
            {"Identificar las oportunidades que tien",
            "Generar y evaluar las alternativas que tiene",
            "Identificar las oportunidades que tiene",
            "Seleccionar unas estrategias e implantarlas"},
            //
            {"Considera que los empleados para trabajar necesitan del grupo para poder sentir confianza en el trabajo y producir",
            "Considera que los empleados evitan en lo posible hacer tareas o asumir responsabilidades",
            "Considera que el empleado requiere un supervisor permanente para realizar sus tareas adecuadamente",
            "Considera que los empleados requieren de relaciones sociales solidas como factor fundamental de productividad"},
            //
            {"Equipo tomador de decisiones y enfoque en el cliente",
            "Junta directiva y enfoque en proyectos de software",
            "Equipo de alto rendimiento y enfoque en procesos",
            "Equipo tomador de decisiones y enfoque en procesos "},
            //
            {"Un modelo administrativo enfocado en el trabajo en equipo, la responsabilidad y autocontrol de los trabajadores en todos los niveles jerárquicos.",
            "Un modelo administrativo enfocado en mejorar las relaciones sociales de los trabajadores como factor fundamental de su productividad",
            "Un modelo administrativo enfocado en el fortalecimiento de la estructura de la organización que de mayor flexibilidad a las tareas que se realizan",
            "Un modelo administrativo enfocado en la productividad individual de los trabajadores"},
            //
            {"Crear un plan de capacitación",
                    "Hacer un mejor reclutamiento y selección",
                    "Diseñar bien los cargos que necesita.",
                    "Especializar el cargo de vendedora."},
            //
            {"Los equipos   empezarán a vender más porque controlan sus actividades.",
                    "Los equipos tendrán mayor estabilidad en su trabajo porque están más satisfechos con lo que hacen.",
                    "Los equipos producirán más y se sentirán más satisfechos.",
                    "Los equipos se sentirán más satisfechos porque entienden el significado de sus trabajos y tienen control sobre lo que hacen."},
            //
            {"Estudio de mercados",
                    "Encuestas y entrevistas",
                    "Análisis del entorno",
                    "Análisis interno"},
            //
            {"El presidente de la junta directiva de la organización",
                    "El administrador",
                    "Los accionistas de la empresa",
                    "El director de planeación"}
    };

    private  String[] respuestas = {
            "Organizar",
            "Direcion general",
            "personas, recursos, objetivos",
            "Estrategias FO",
            "La técnica de escenarios",
            "Administración científica",
            "Administración clásica",
            "Hacer",
            "Controlar",
            "Entrevistas",
            "Diagrama de Gantt",
            "Funcionalización",
            "Estructura organizacional por división",
            "Interpersonales",
            "Estrategia Corporativa",
            "Por clientes y Geográfica",
            "Planear e implementar un adecuado proceso de integración de personal.",
            "Evaluación del desempeño 360 grados",
            "Matricial",
            "Cliente/producto",
            "Cultura",
            "Outsourcing",
            "Colonialismo Industrial",
            "Organización, ya que la asignación de recursos incluso financieros es propia de esta función",
            "Identifica y diseña las actividades que son necesarias para obtener los objetivos o metas de una organización",
            "Competencia creciente en un mercado globalizado.",
            "La motivación y la competencia",
            "La Dirección no tiene una buena comunicación para trasmitir a los integrantes del equipo los objetivos de la planeación.",
            "Interpersonales, Informativos, Decisorios",
            "El administrador",
            "Análisis del entorno",
            "Los equipos se sentirán más sa tisfechos porque entienden el significado de sus trabajos y tienen control sobre lo que hacen",
            "Diseñar bien los cargos que necesita",
            "Le permite aplicar a los procesos de manufactura con un equipo determinado",
            "Tomando una decisión",
            "Identificar las oportunidades que tiene",
            "Considera que los empleados evitan en lo posible hacer tareas o asumir responsabilidades",
            "Equipo tomador de decisiones y enfoque en el cliente",
            "Un modelo administrativo enfocado en el trabajo en equipo, la responsabilidad y autocontrol de los trabajadores en todos los niveles jerárquicos",
            "Diseñar bien los cargos que necesita.",
            "Los equipos se sentirán más satisfechos porque entienden el significado de sus trabajos y tienen control sobre lo que hacen.",
            "Análisis del entorno",
            "El director de planeación"};

    private int puntaje = 0;
    private int screens = 0;
    private int randy = 0;
    private TextView enunciadoPregunta;
    private Random randomEnun= new Random();
    private Button respues1;
    private Button respues2;
    private Button respues3;
    private Button respues4;
    private Handler waiter = new Handler();
    private long start, end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_trivia);

        enunciadoPregunta = (TextView) findViewById(R.id.enunPreg);
        enunciadoPregunta.setMovementMethod(new ScrollingMovementMethod());
        respues1 = (Button) findViewById(R.id.opcionRes1);
        respues1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
        respues2 = (Button) findViewById(R.id.opcionRes2);
        respues2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
        respues3 = (Button) findViewById(R.id.opcionRes3);
        respues3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
        respues4 = (Button) findViewById(R.id.opcionRes4);
        respues4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));

        Utiles.startCon = System.currentTimeMillis();
        start = System.currentTimeMillis();

        pantallaRandom();
    }

    public void pantallaRandom(){
        int randomEnunciados = randomEnun.nextInt(enunciadosPreg.length);
        enunciadoPregunta.setText(enunciadosPreg[randomEnunciados]);
        respues1.setText(opcionesRespuestas[randomEnunciados][0]);
        respues2.setText(opcionesRespuestas[randomEnunciados][1]);
        respues3.setText(opcionesRespuestas[randomEnunciados][2]);
        respues4.setText(opcionesRespuestas[randomEnunciados][3]);
        randy = randomEnunciados;
    }

    public void nextScreen (final View vista){
        vista.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
        waiter.postDelayed(new Runnable() {
            @Override
            public void run() {
                //elHaceNada(vista);
            }
        }, 5000);
        TextView opcionPulsada = (TextView) vista;
        if (opcionPulsada.getText().toString() == respuestas[randy]) {
            puntaje++;
        }
        else if(opcionPulsada.getText().toString() != respuestas[randy]){
        }
        vista.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
        screens++;
        if(screens == 3){
            respues1.setEnabled(false);
            respues1.setText("");
            respues1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
            respues2.setEnabled(false);
            respues2.setText("");
            respues2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
            respues3.setEnabled(false);
            respues3.setText("");
            respues3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
            respues4.setEnabled(false);
            respues4.setText("");
            respues4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
            if(puntaje >= 2 && Login.user.getLevel().equals("1")){
                if(puntaje == 3) {
                    guardarRes(1, "2", 1);
                    enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" + "Obtuviste 1 moneda"+
                            "\n" +
                            "\n" + "Obtuviste 1 insignia");
                }
                else{
                    guardarRes(1, "2", 0);
                    enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" + "Obtuviste 1 moneda"+
                            "\n" +
                            "\n" + "No obtuviste insignias");
                }
            }
            else if(puntaje >= 2 && Login.user.getLevel().equals("2")){
                if(puntaje == 3) {
                    guardarRes(2, "3", 1);
                    enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" + "Obtuviste 2 moneda"+
                            "\n" +
                            "\n" + "Obtuviste 1 insignia");
                }
                else{
                    guardarRes(2, "3", 0);
                    enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" + "Obtuviste 2 moneda"+
                            "\n" +
                            "\n" + "No obtuviste insignias");
                }
            }
            else if(puntaje < 2 && Login.user.getLevel().equals("1")){
                guardarRes(0, "2", 0);
                enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" + "No obtuviste monedas"+
                        "\n" +
                        "\n" + "No obtuviste insignias");
            }
            else if(puntaje < 2 && Login.user.getLevel().equals("2")){
                guardarRes(0, "3", 0);
                enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" + "No obtuviste monedas"+
                        "\n" +
                        "\n" + "No obtuviste insignias");
            }
            else if(Login.user.getLevel().equals("FIN")){
                if(puntaje == 3){
                    guardarRes(1, "FIN", 0);
                    enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" + "Obtuviste 1 moneda"+
                            "\n" +
                            "\n" + "No obtuviste insignias");
                }
                else{
                    guardarRes(0, "FIN", 0);
                    enunciadoPregunta.setText("Tuvite bien "+puntaje+" pregunta(s)."+
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" + "No obtuviste monedas"+
                            "\n" +
                            "\n" + "No obtuviste insignias");
                }

            }
            waiter.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), Mapa.class);
                    i.putExtra("back", "no");
                    Utiles.terminarConexion();
                    startActivity(i);
                }
            }, 3000);
            enunciadoPregunta.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
        else {
            pantallaRandom();
        }
    }

    public void guardarRes(int res, String level, int insignia){
        end = System.currentTimeMillis();
        long totaltime = (end-start)/1000;
        int puntajes = Integer.parseInt(Login.user.getCoins());
        puntajes += res;
        int insignias = Integer.parseInt(Login.user.getBadges());
        insignias += insignia;
        Login.user.setLevel(level);
        Login.user.setCoins(String.valueOf(puntajes));
        Login.user.setBadges(String.valueOf(insignias));
        TiempoConexionJuego atr = new TiempoConexionJuego(
                Utiles.getFecha(),
                Login.user.getCode(),
                Login.user.getGroup(),
                "Trivia",
                String.valueOf(puntajes),
                String.valueOf(totaltime),
                level,
                String.valueOf(insignias));
        atr.execute();
    }
}
