package jp.ac.waseda.cs.washi.gameaiarena.functions;

public interface Action2<TArg1, TArg2> {
	void invoke(TArg1 arg1, TArg2 arg2);
}
