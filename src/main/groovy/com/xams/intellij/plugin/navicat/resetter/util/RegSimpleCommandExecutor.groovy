package com.xams.intellij.plugin.navicat.resetter.util

final class RegSimpleCommandExecutor {

    private final static String REG_KEY = "REG"
    private final static String QUERY = "QUERY"
    private final static String DELETE = "DELETE"

    static Process query(List<String> commands) {
        List<String> cmd = [REG_KEY, QUERY]
        cmd.addAll(commands)
        String[] args = []
        return execute(cmd.toArray() as String[], args)
    }

    static Process delete(List<String> commands) {
        List<String> cmd = [REG_KEY, DELETE]
        cmd.addAll(commands)
        String[] args = []
        return execute(cmd.toArray() as String[], args)
    }

    private static Process execute(String[] cmd, String[] args) {
        return Runtime.getRuntime().exec(cmd, args, null)
    }

}
