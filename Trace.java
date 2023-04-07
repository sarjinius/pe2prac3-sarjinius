import java.util.ArrayList;
import java.util.List;

/**
 * @author A0255894L
 */

class Trace<T> {
  private T value;
  private List<T> history;

  public Trace(T value, List<T> history) {
    this.value = value;
    this.history = history;
  }

  @SafeVarargs
  public static <T> Trace<T> of(T... items) {
    List<T> list = new ArrayList<>();

    for (int i = 1; i < items.length; i++) {
      list.add(items[i]);
    }

    return new Trace<T>(items[0], list);
  }

  public T get() {
    return this.value;
  }

  public List<T> history() {
    return this.history;
  }

  public Trace<T> back(int i) {
    if (history.size() == 0) {
      return this;
    } else if (i > 0) {
      List<T> list = new ArrayList<>();
      list.addAll(this.history);
      T val = list.remove(list.size() - 1);

      return new Trace<T>(val, list).back(i - 1);
    } else {
      return this;
    }
  }

  public Trace<T> map(Transformer<? super T, ? extends T> mapper) {
    List<T> newHistory = new ArrayList<>();
    newHistory.addAll(this.history);
    newHistory.add(this.value);

    return new Trace<T>(mapper.transform(this.value), newHistory);
  }

  public Trace<T> flatMap(Transformer<? super T, 
                          ? extends Trace<? extends T>> mapper) {
    Trace<? extends T> newTrace = mapper.transform(this.value);
    T newVal = newTrace.value;
    List<T> newHistory = new ArrayList<>();
    newHistory.add(this.value);
    newHistory.addAll(newTrace.history);

    return new Trace<T>(newVal, newHistory);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof Trace) {
      Trace<?> trace = (Trace<?>) obj;

      if (this.value == null) {
        return trace.value == null;
      }

      if (this.value.equals(trace.value) 
          && this.history.size() == trace.history.size()) {
        int len = this.history.size();

        for (int i = 0; i < len; i++) {
          if (!this.history.get(i).equals(trace.history.get(i))) {
            return false;
          }
        }

        return true;
      }
    }

    return false;
  }
}
