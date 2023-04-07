/**
 * @author xxx
 */

class Trace<T> {

  private T object;
  private List<T> list;

  // Change the constructor
  public Trace() {

  }

  // have fun
  public static <T> Trace<T> of(???) {
  }

  public T get() {

  }

  public List<T> history() {

  }

  public Trace<T> back(int i) {

  }

  public Trace<T> map(Transformer<? super T, ? extends T> mapper) {

  }

  public Trace<T> flatMap(Transformer<? super T, ? extends Trace<? extends T>> mapper) {

  }

  @Override
  public boolean equals(Object obj) {
    
  }
}

