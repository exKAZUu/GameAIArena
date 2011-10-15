package jp.ac.waseda.cs.washi.gameaiarena.functions;

public interface Func2<TResult, TArg1, TArg2> {
	TResult invoke(TArg1 arg1, TArg2 arg2);
}
