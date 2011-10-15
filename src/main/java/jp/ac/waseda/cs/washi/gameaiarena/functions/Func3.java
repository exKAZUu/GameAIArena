package jp.ac.waseda.cs.washi.gameaiarena.functions;

public interface Func3<TResult, TArg1, TArg2, TArg3> {
	TResult invoke(TArg1 arg1, TArg2 arg2, TArg3 arg3);
}
