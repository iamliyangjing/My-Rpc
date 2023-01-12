package com.lyj.compress;

import com.lyj.extension.SPI;

/**
 * @program: My-Rpc
 * @description: compress
 * @author: lyj
 * @create: 2023-01-11 00:20
 **/
@SPI
public interface Compress {


    byte[] compress(byte[] bytes);


    byte[] decompress(byte[] bytes);

}
