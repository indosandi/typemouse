package co; 
public final class MyValues {
      public static final String VALUE1 = "foo";
        public static final String VALUE2 = "bar";
        private MyValues(){
         //this prevents even the native class from 
         //    //calling this ctor as well :
                throw new AssertionError();
          }

}
