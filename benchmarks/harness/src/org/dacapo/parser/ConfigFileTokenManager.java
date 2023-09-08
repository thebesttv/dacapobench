/* Generated By:JavaCC: Do not edit this line. ConfigFileTokenManager.java */
package org.dacapo.parser;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;

/** Token Manager. */
public class ConfigFileTokenManager implements ConfigFileConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x3fffffffe000L) != 0L)
         {
            jjmatchedKind = 46;
            return 5;
         }
         if ((active0 & 0x140L) != 0L)
            return 2;
         return -1;
      case 1:
         if ((active0 & 0x100L) != 0L)
            return 0;
         if ((active0 & 0x3fffffffe000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 1;
            return 5;
         }
         return -1;
      case 2:
         if ((active0 & 0x3efdffffe000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 2;
            return 5;
         }
         if ((active0 & 0x10200000000L) != 0L)
            return 5;
         return -1;
      case 3:
         if ((active0 & 0x3e6dd9ffc000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 3;
            return 5;
         }
         if ((active0 & 0x9026002000L) != 0L)
            return 5;
         return -1;
      case 4:
         if ((active0 & 0x3e69c8f9c000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 4;
            return 5;
         }
         if ((active0 & 0x411060000L) != 0L)
            return 5;
         return -1;
      case 5:
         if ((active0 & 0x3e0108188000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 5;
            return 5;
         }
         if ((active0 & 0x68c0e14000L) != 0L)
            return 5;
         return -1;
      case 6:
         if ((active0 & 0x180000188000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 6;
            return 5;
         }
         if ((active0 & 0x260108000000L) != 0L)
            return 5;
         return -1;
      case 7:
         if ((active0 & 0x180000188000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 7;
            return 5;
         }
         return -1;
      case 8:
         if ((active0 & 0x180000100000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 8;
            return 5;
         }
         if ((active0 & 0x88000L) != 0L)
            return 5;
         return -1;
      case 9:
         if ((active0 & 0x180000100000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 9;
            return 5;
         }
         return -1;
      case 10:
         if ((active0 & 0x180000000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 10;
            return 5;
         }
         if ((active0 & 0x100000L) != 0L)
            return 5;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 51);
      case 41:
         return jjStopAtPos(0, 52);
      case 44:
         return jjStopAtPos(0, 50);
      case 47:
         return jjMoveStringLiteralDfa1_0(0x140L);
      case 59:
         return jjStopAtPos(0, 53);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x6000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x38000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0xc0000L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x300000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x1800000L);
      case 106:
         return jjMoveStringLiteralDfa1_0(0x4000000L);
      case 107:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x38000000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x200200000000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x7c00000000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1c8000000000L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(1, 8, 0);
         break;
      case 47:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x204000000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x228142108000L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x1c0400000000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x1819a10000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x20080000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000002000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x6000000000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x80004000L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x6000000000L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x202000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 108:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 40, 5);
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x830018000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000080000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x1e0104000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0xc0024000L);
      case 119:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(2, 33, 5);
         break;
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x8001000000L);
      case 122:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 95:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x108000L);
      case 101:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 36, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x3c4019220000L);
      case 103:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(3, 29, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 104:
         return jjMoveStringLiteralDfa4_0(active0, 0x40004000L);
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L);
      case 112:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(3, 25, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000L);
      case 115:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(3, 13, 5);
         else if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(3, 26, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x20000440000L);
      case 116:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 121:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x3c0000000000L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x100000000L);
      case 100:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(4, 24, 5);
         break;
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L);
      case 104:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x40004000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000190000L);
      case 115:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 5);
         else if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(4, 18, 5);
         else if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(4, 28, 5);
         return jjMoveStringLiteralDfa5_0(active0, 0x200000L);
      case 116:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(4, 34, 5);
         return jjMoveStringLiteralDfa5_0(active0, 0x400000L);
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x2080000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(5, 30, 5);
         return jjMoveStringLiteralDfa6_0(active0, 0x1c0000000000L);
      case 101:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(5, 35, 5);
         break;
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x180000L);
      case 109:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      case 111:
         return jjMoveStringLiteralDfa6_0(active0, 0x20000000000L);
      case 112:
         return jjMoveStringLiteralDfa6_0(active0, 0x100000000L);
      case 114:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(5, 14, 5);
         else if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(5, 23, 5);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 38, 5);
         break;
      case 115:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(5, 22, 5);
         return jjMoveStringLiteralDfa6_0(active0, 0x8000000L);
      case 116:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(5, 21, 5);
         else if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(5, 31, 5);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 37, 5);
         return jjMoveStringLiteralDfa6_0(active0, 0x200000000000L);
      case 121:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(5, 16, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa7_0(active0, 0x180000000000L);
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000L);
      case 101:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(6, 27, 5);
         break;
      case 103:
         return jjMoveStringLiteralDfa7_0(active0, 0x80000L);
      case 110:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 41, 5);
         break;
      case 112:
         return jjMoveStringLiteralDfa7_0(active0, 0x100000L);
      case 115:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 42, 5);
         else if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 45, 5);
         break;
      case 117:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(6, 32, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 104:
         return jjMoveStringLiteralDfa8_0(active0, 0x80000L);
      case 108:
         return jjMoveStringLiteralDfa8_0(active0, 0x80000000000L);
      case 109:
         return jjMoveStringLiteralDfa8_0(active0, 0x100000000000L);
      case 114:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000L);
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x100000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x80000100000L);
      case 107:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(8, 15, 5);
         break;
      case 111:
         return jjMoveStringLiteralDfa9_0(active0, 0x100000000000L);
      case 116:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(8, 19, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa10_0(active0, 0x100000000000L);
      case 109:
         return jjMoveStringLiteralDfa10_0(active0, 0x80000000000L);
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x100000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa11_0(active0, 0x100000000000L);
      case 105:
         return jjMoveStringLiteralDfa11_0(active0, 0x80000000000L);
      case 110:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(10, 20, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 108:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStartNfaWithStates_0(11, 44, 5);
         break;
      case 116:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(11, 43, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 19;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 49)
                        kind = 49;
                     jjCheckNAdd(18);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(0, 2);
                  else if (curChar == 36)
                  {
                     if (kind > 46)
                        kind = 46;
                     jjCheckNAdd(5);
                  }
                  else if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 2;
                  if (curChar == 48)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 0:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if ((0xffff7fffffffffffL & l) != 0L && kind > 7)
                     kind = 7;
                  break;
               case 2:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 4:
                  if (curChar != 36)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0x3ff601000000000L & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAdd(5);
                  break;
               case 6:
                  if (curChar == 48)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 9:
                  if (curChar == 34)
                     jjCheckNAddStates(0, 2);
                  break;
               case 10:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 12:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 13:
                  if (curChar == 34 && kind > 48)
                     kind = 48;
                  break;
               case 14:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  break;
               case 15:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 16:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(15);
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 49)
                     kind = 49;
                  jjCheckNAdd(18);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
               case 5:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjCheckNAdd(5);
                  break;
               case 1:
                  if (kind > 7)
                     kind = 7;
                  break;
               case 7:
                  if ((0x100000001000000L & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjCheckNAdd(8);
                  break;
               case 8:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjCheckNAdd(8);
                  break;
               case 10:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 11:
                  if (curChar == 92)
                     jjAddStates(7, 9);
                  break;
               case 12:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 7)
                     kind = 7;
                  break;
               case 10:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 19 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_3()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_3(0x800L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_3(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(1, 11);
         break;
      default :
         return 2;
   }
   return 2;
}
private int jjMoveStringLiteralDfa0_1()
{
   return jjMoveNfa_1(0, 0);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 9)
                        kind = 9;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 9)
                     kind = 9;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_2(0x400L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_2(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(1, 10);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
   10, 11, 13, 10, 11, 15, 13, 12, 14, 16, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
"\141\162\147\163", "\141\165\164\150\157\162", "\142\145\156\143\150\155\141\162\153", 
"\142\151\156\141\162\171", "\142\171\164\145\163", "\143\154\141\163\163", 
"\143\157\160\171\162\151\147\150\164", "\144\145\163\143\162\151\160\164\151\157\156", "\144\151\147\145\163\164", 
"\145\170\151\163\164\163", "\146\151\154\164\145\162", "\146\151\170\145\144", "\153\145\145\160", 
"\152\141\162\163", "\154\151\143\145\156\163\145", "\154\151\156\145\163", "\154\157\156\147", 
"\155\145\164\150\157\144", "\157\165\164\160\165\164", "\160\145\162\137\143\160\165", "\162\141\167", 
"\163\150\157\162\164", "\163\151\156\147\154\145", "\163\151\172\145", "\163\164\144\157\165\164", 
"\163\164\144\145\162\162", "\164\145\170\164", "\165\162\154", "\166\145\162\163\151\157\156", 
"\164\150\162\145\141\144\163", "\164\150\162\145\141\144\55\154\151\155\151\164", 
"\164\150\162\145\141\144\55\155\157\144\145\154", "\162\145\160\145\141\164\163", null, null, null, null, "\54", "\50", "\51", 
"\73", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_SINGLE_LINE_COMMENT",
   "IN_FORMAL_COMMENT",
   "IN_MULTI_LINE_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, 1, 2, 3, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x3fffffffffe001L, 
};
static final long[] jjtoSkip = {
   0xe3eL, 
};
static final long[] jjtoSpecial = {
   0xe00L, 
};
static final long[] jjtoMore = {
   0x11c0L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[19];
private final int[] jjstateSet = new int[38];
private final StringBuffer jjimage = new StringBuffer();
private StringBuffer image = jjimage;
private int jjimageLen;
private int lengthOfMatch;
protected char curChar;
/** Constructor. */
public ConfigFileTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ConfigFileTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 19; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 4 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try { input_stream.backup(0);
            while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
               curChar = input_stream.BeginToken();
         }
         catch (java.io.IOException e1) { continue EOFLoop; }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 12)
         {
            jjmatchedKind = 12;
         }
         break;
       case 2:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_2();
         if (jjmatchedPos == 0 && jjmatchedKind > 12)
         {
            jjmatchedKind = 12;
         }
         break;
       case 3:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_3();
         if (jjmatchedPos == 0 && jjmatchedKind > 12)
         {
            jjmatchedKind = 12;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           matchedToken.specialToken = specialToken;
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
           {
              matchedToken = jjFillToken();
              if (specialToken == null)
                 specialToken = matchedToken;
              else
              {
                 matchedToken.specialToken = specialToken;
                 specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
           }
           else
              SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
        MoreLexicalActions();
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      case 7 :
         image.append(input_stream.GetSuffix(jjimageLen));
         jjimageLen = 0;
                   input_stream.backup(1);
         break;
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
