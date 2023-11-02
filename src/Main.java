import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Deque<Integer> ca = new ArrayDeque<>();
        int i = 0;
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        ca.add(++i);
        System.out.println( Arrays.toString(ca.toArray()) );
        Deque<Integer> autreCa = inverserDeque01(ca);
        System.out.println( Arrays.toString(ca.toArray()) );
        System.out.println( Arrays.toString(autreCa.toArray()) );

        inverserDeque02(autreCa);
        System.out.println( Arrays.toString(autreCa.toArray()) );

        System.out.println(verifierStringMotherFucker("}"));
        System.out.println(verifierStringMotherFucker(null));
        System.out.println(verifierStringMotherFucker("null (asdfasfsa{asdfsafd}fdsaf)asdfasfd(asdfasdf)asdfasfd[asdfasdfsad]asdfasf"));
        System.out.println(verifierStringMotherFucker("allo { asdfs"));
    }

    /* 6.1.1
    Écrivez une méthode qui reçoit une pile (sous forme d’un Deque) en entrées et
    retourne une pile (Deque) qui contient les éléments de la pile en entrées en ordre
    inverse. La pile en entrées va être vidée par l’opération.*/
    private static <T> Deque<T> inverserDeque01(Deque<T> deque){
        Deque<T> resultat = null;
        if(deque != null) {
            resultat = new ArrayDeque<>(deque.size());
            // Tant que deque n'est pas vidé avec deque.poll() la boucle continue
            while (!deque.isEmpty()) {
                // deque.poll() prend l'item à l'index 0
                // et resultat.addFirst place l'item au début de resultat
                // les items de resultat sont pousser vers la droite
                resultat.addFirst(deque.poll());
            }
        }
        return resultat;
    }

    /* 6.1.2
    Même chose qu’au numéro précédant, mais cette fois nous voulons vider la pile
    en entrées et replacer les éléments en ordre inverse dans la même pile.*/
    private static <T> void inverserDeque02(Deque<T> deque) {
        if (deque != null) {
            // On réutilise la méthode faite plus tôt pour avoir la liste inverser
            Deque<T> temp = inverserDeque01(deque);
            while (!temp.isEmpty()) {
                // on copie le deque temps dans le deque d'entrer
                // poll() prends l'item à l'index 0
                // et add met l'item à la fin de deque
                deque.add(temp.poll());
            }
        }
    }

    /* 6.1.3
    Lorsque nous avons une chaine de caractères contenant des parenthèses,
    des crochets et des accolades, il est possible de vérifier si ces éléments
    ferment correctement en utilisant une Pile de caractères.
        • Lorsque nous rencontrons une ouverture (parenthèse, crochet ou accolade),
        nous empilons le caractère rencontré.
        • Lorsque nous rencontrons une fermeture, nous vérifions le sommet de la Pile
        pour voir si nous avons le même type (parenthèse, crochet ou accolade). Si non,
        une erreur, si oui, nous dépilons.
    Écrivez une méthode qui reçoit une String et fait cette vérification. Retournez
     un boolean indiquant si le parenthesage est correct ou non. Remarque : la classe
     Java encapsulant le type primitif ‘char’ est Character.*/
    private static boolean verifierStringMotherFucker(String str){
        if(str == null) return false;

        // Ici j'ai un tableau de 2 ArrayList
        List[] chars = new List[]{
                new ArrayList<>(Arrays.asList('{', '[', '(')),
                new ArrayList<>(Arrays.asList('}', ']', ')'))
        };
        Stack<Character> stack = new Stack<>();
        boolean resultat = true;
        int i = 0;
        int index;

        // La boucle continue jusqu'à ce qu'elle trouve une incohérance
        // ou que la vérification soit fini
        while (resultat && i < str.length()){
            // Je fais chercher le caractère à vérifier
            Character lettre = str.charAt(i);
            // Le caractère est un caractère d'ouverture
            if(chars[0].contains(lettre)){
                // Je l'ajoute
                stack.push(lettre);
            } else if ( (index = chars[1].indexOf(lettre)) >= 0 ) {
                //Sinon le caractère est un caractère de sortie
                // Si la stack est vide ou le caractère sortant ne correspond pas
                // au caractère d'entrer, alors il y a une erreur dans les String
                if(stack.isEmpty() || !stack.pop().equals(chars[0].get(index))){
                    resultat = false;
                }
            }
            i++;
        }
        // Si le resultat est vrai, mais que la stack ne l'ai pas
        // alors il y a au moins un caractère de sortie manquent.
        return resultat && stack.isEmpty() ;
    }
}