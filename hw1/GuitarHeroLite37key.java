import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite37key {

    private static GuitarString[] strings= new GuitarString[37];
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        int frep = 0;
        for(int i = 0; i < strings.length; i++){
            strings[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {
            int index = 0;
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                /*make sure it won't crash*/
                if (index == -1) {
                    index = 0;
                }

                strings[index].pluck();
            }

        double sample = 0;
        for(int i = 0; i < strings.length; i++){
                sample += strings[index].sample();
        }
        /* compute the superposition of samples */
       // double sample = strings[index].sample();

        /* play the sample on standard audio */
        StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
        for(int i = 0; i < strings.length; i++){
                strings[i].tic();
            }
        }
    }
}
