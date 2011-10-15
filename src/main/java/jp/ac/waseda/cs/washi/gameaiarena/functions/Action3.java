package jp.ac.waseda.cs.washi.gameaiarena.functions;

public interface Action3<TArg1, TArg2, TArg3> {
	void invoke(TArg1 arg1, TArg2 arg2, TArg3 arg3);
}
