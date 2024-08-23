extern vec2 center;
extern float zoom;
extern float depth;


vec4 effect(vec4 color, Image texture, vec2 uv, vec2 xy) {
    float oldZI = 0;
    float oldZR = 0;

    uv -= .5;
    uv *= 4;
    uv /= zoom;

    uv.x += center.x;
    uv.y += center.y;


    float newZI;
    float newZR;

    //float depth = 400;

    //JULIA SET

    //oldZI = uv.y;
    //oldZR = uv.x;

    //uv = vec2(-.8,.156);

    //JULIA SET END

    for (int j = 0; j < depth; j += 1) {
        for (int i = 0; i < j; i ++) {

            newZI = 2 * oldZR * oldZI;
            newZR = oldZR * oldZR - oldZI * oldZI;

            newZI += uv.y;
            newZR += uv.x;

            oldZI = newZI;
            oldZR = newZR;

            if ((oldZI*oldZI+oldZR*oldZR)>100) {
                return vec4(0,0,j/10.0,1);
            }
        }
    }
    float magnitude = 0;

    return (1-magnitude) * vec4(0,0,0,1) + magnitude * vec4(0,0,1,1);

    if (sqrt(newZI * newZI + newZR * newZR) > .00001) {
        return vec4(0,0,0,1);
    }

    return vec4(0,1,1,1);
}