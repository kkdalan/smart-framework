package org.smart4j.framework.proxy;

public interface Proxy {

	public Object doProxy(ProxyChain proxyChain) throws Throwable;
}
