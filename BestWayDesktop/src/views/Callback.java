package views;

public interface Callback {
  // retorna true para encerrar a pilha de chamadas
  // retorna false para continuar empilhando
  public boolean callback();
}