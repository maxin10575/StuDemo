package com.mx.example.algorithm;//package com.example.algorithm;
//
///**
// * TODO:
// *
// * @author mx
// * @version 1.0, 2018/11/9/18:13
// */
//public class RedisHash {
//    public static void main(String[] args) {
//
//    }
//    public static void redisHash(){
//
//    }
//    uint32_t
//    murmur_hash2(char *data, size_t len)
//    {
//        uint32_t h, k;
//
//        h = 0 ^ len;
//
//        while (len >= 4) {
//            k = data[0];
//            k |= data[1] << 8;
//            k |= data[2] << 16;
//            k |= data[3] << 24;
//
//            k *= 0x5bd1e995;
//            k ^= k >> 24;
//            k *= 0x5bd1e995;
//
//            h *= 0x5bd1e995;
//            h ^= k;
//
//            data += 4;
//            len -= 4;
//        }
//
//        switch (len) {
//            case 3:
//                h ^= data[2] << 16;
//            case 2:
//                h ^= data[1] << 8;
//            case 1:
//                h ^= data[0];
//                h *= 0x5bd1e995;
//        }
//
//        h ^= h >> 13;
//        h *= 0x5bd1e995;
//        h ^= h >> 15;
//
//        return h;
//    }
//}
