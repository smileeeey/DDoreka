<template>
  <v-card
      class="mx-auto mt-3"
      max-width="400"
      flat
    >
      <v-card-actions class="py-0">
        <v-list-item>
          <v-list-item-avatar
            size="30"
            @click="gotoProfile"
          >
            <v-img
              src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxITEhUSEhIVFRUVGBcWFRYVFRUYFxUWFxUWFxcVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OFxAQFy0dHx0tKy0tLS0tLS0tLS0tKy0rLS0rKy0rLS0tLSstLSstLSstLi0tLS0tLS03LS0tODA3K//AABEIAOEA4QMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAAAwQFBgcBAgj/xAA6EAABAwIDBgQEBAYDAQEBAAABAAIDBBEFITEGEkFRYXEiMoGxE5Gh8AdCwdEUI1Jy4fEzYoJTwhb/xAAZAQADAQEBAAAAAAAAAAAAAAAAAgMBBAX/xAAjEQEBAAICAgICAwEAAAAAAAAAAQIRAyExQRJRBDIiYfAT/9oADAMBAAIRAxEAPwDXEIQsMEIQgBCEIAQhCAEIQgBCF1aHEIJSJmHDNALFeS9ROK4zDALzSBvIX17BUnFvxMjbcRRk8i4/oEly02S1pD5raprPicbfNI0dN4LEq/byokzc/dH9LRa/qoxm1Ibe7S4k3vc3+aX5X6Gv7b0MWiOkjT6hLxYi08cufBYP/wD08DrZPaee86wXul2rkjuWTnLRpzGqPlRpv8T76G6UBWW7M7dXIDyLnULQKfEA4XCaZbZpJ3QmTaoc0uyYFMCyFxpXVoC4uoWBxC6hACEIQAhCEAIQhaAhC6gOIXUnJM1upQCiRqZ2sFyVCYztTHAwvOg9+SybaX8QJZv+M7l/zXztya3h3SXP6bMWnY5tfTwAlzwTwaD+nFZ1jn4izSnchvGDlkfEf0Coj5nvdvEkknMnVOImCNpcfMfZJ2bqF66te870j3OdxJdf6pmJmnimM8pJKGbw1bcLZGW09e4DhcJJ0Tcrc15hky0y0K9MZdzc9Sb+i3ZNOT04JySTYLaa/eikXbvDNNZn2WgrRndcCT+61fZPGviRAOObRujqOCxsyk9FMYPiz43eA2sEtn0I2f8AjHDmntHiSqeD40Jow468R1T9sgvkUsrdLxTVN09aVVcMq1YqaS6rKw5QhCZoQhCAEIQsAQhdQAhCFoCELjygE5H26lQW0ONxU0ZfIc+X6JTHsaZTxukcegHM8Fg21O0MlTI5znXFzujgBzU8svUNjj7ru1m1ctTISXHdGTW8AL9NSq+1hJ6n6Lw0f4TxrLDr+qxt7L07Bfsk6lrnmw7/ALJxCzQfNT+zuE/Efpexukyy0phx/Ils9sp8SznC6ukOysO7Yxj5KyYbh4Y0AKQEC57cq6JjjJrTMsZ2PaAXMFuY/ZVSrwB7BexyW5yU11GVmGtPAJpyZQuXFjkweaBzdcl0Rm17a/QLSsd2ba4GwsqDW0pikIcVbDOZObk4riipXAd0kKghPKqn4+yj3KsSWzZXGxEQ13lJ14g/stIa64vw17hYY0m61vZCYupmXdcjLW9rcEmU02VYKWUtKsmG1l1VU+oZ7FGNYu8Ul0qoqhnuFJscqh7XEXQtaF1CEAIQhACEIQAm1ZMGtJJtYXTg/RZ7+J20LIYjTscPiPsHW1DeJPdLldRsm1F252g/iZfhtd/Lbb6a2VJqTnl6ZJ9LPug8e/8AhNYpC43s0AKch69UkYALiM+CViZxXCb2+8k8pqbesOaLW44nlDS726BmXFads3hIiYBxOqoODUM0MrXgbwGZ6BbDhO69gIUMu66Z/GF4mZJUMSgjQQjTNm8rE3kjT5yRkalsNKgq+LXJZttZhxNyG+v6rVqtmSpm0UJseQusxuq3LH5RmDI8iCo6WPop6vbuu+7KPliDhdt78R+y65XBlNUwhZcq9bK1fwpGRkeGUGx5PHD1CpMTgCL6cVcqxjXUolj1js9p6g5hZkJF6uvcDs0yoan4kbHj8wB+afwMzSwVYcLep+EqAw0KegVoUuhcQma9Lq4hACEIQAgoTetqGsY5ziGtAJJPADUoCO2ixptPEXanQAak8gvn/F6kySySvN3veT21v8uC0PaXF3uilqpLNjA3YWEZ3dk0nqfNblZZU15LiT1JUbd1STUN6x2YYNf34J1DDZoP2SvFDDvuLnGw6+wT6RhBBOv5RyC0SbJRx5gAXPT2UxhTW726TZwsLcuZSeE0pzfxGnuuPwmoEnxA3VwcHDhcfm42UrZenRhLjq6203A6UBouNRn2VqwuINvbRVjZMkx5kHdNrjQ87K3UgyUsJ2ty3Z3upKQL2XppU17Wgk39FW2OeSuvKSeUyixyFxsTunk7j2TwPBzGanVDSoCreNw3BVomChMXjuwpKpjWWYvGAbKFmBYbjQqaxmS5d0UWW70fbMen+F04+HFyfsYVFjnx91KbP19mSwuORa7dvztooKV3JK0JG8LmyezpPfbTNlCRSxA6hoCscUirmFu3d1vAjI+imY3KcosWTDZVZKY3CqeFjRWqj0VsSnNkL0hM1xdXF1aHEIQgOOKq2PVHxXfCbnGwh0h/rcD4YwOOevZTldOfK3U8eXMqo49iApqdz2ea5DL282d3/op5U2M2zz8TMU3pm07fLD5zzkcBftYZKnvyBPolKuQvfckkuNyTxN7kryRct7kntfRLDVLYXA0NDnZ2yaOZOpTimo/iSW1/qP6JtTTHQa2sOg5AK87IYKSwPflvG/UqeeWl+LGXyRoMJ3iG2459sr/orbRYU1tyfkn2G0ABLrW1y6fYCdZE7rc+ZUdOi5a8EaWLkLDkFI/EAACSuGCyQBuVs6TvZ6M14fEOKQrJZGRkxNDn8ATYdVC4nir4mRyf8jpHhgjaCHA2ucullvkTrupKuweOYWOR4HJQF6ikduuzZfUZghOML2ohmPhdY8lPPLZW2cLgpd+ja9m8VU2Rgc3ioXHpt2NyVgpHQyOAN2OzHdQO2dRZnus81viWqBWu3nnrdI0RyI6/oms0/wDM9V6hks49QCuv08+3dRlULOIXiDzDulcQ810lCcwn9F9tAwOUghpNx5mn3CtVO66puCv8p4AK2UDuCh7PVlwtytdHoqhhxzVtoDkr4pniF264na4uoXLoASU0nAL299gkW31PoPvisBhXO3G55E8eQ1P0CyjbzEt5gF/MRuj+lgB+pOav+1FQXO+ENXENd0YDdxvw0sse2urRLO4tya3wtA5Dio5XdWwmptXgfFdKRHP6JM+yeUUNwD/Vn6LWJzZnDfiPF/8AQWx4TShrRlkAPkFTNkMNtmeY/ey0KNtgBpl/pc9u66tfGaIPcA0lzt1ouXFOaJrDHvMIscxbiDxBSbo2kFpFwdQmr6J0UG5TNA3G2YHXIA4A2zss2NQjiM9j0Tyijyuo2lnM1M2SSPcfdzXNzyc0kEi+dja47qawdh+ELoxm63LqOyNyUNjeBRVLC2QZ6gjIg81YXtSBYmsJGXz7LCnY4NBLy4Fst82208PLnzVm2bqZCwB4zGR69VYainB1CaNhDdFPK2+VscpJqR4rCs821qLAq+1sgsVk+2VdvS7vAZLcJvKE5L8cKqU7rPSkJ8V+iaznPsl6Q+boF1vPea8ZjskacZhOK0ZBJwR6Hqt303Xa5YGLxdQforRh5z+SreE+FgdwOThyPPsrLh48Z7BQ9nqxYfqrdh+iqdA3NWvD9F0Ypn6EIVAF5cbLqTkF8hx+7pQ8DM34DTuuTOtmdGjPulTlYKExyq8O43V1yf7dFluo2TdVLaXEQxksx8zvCxvysflcrJqjMk/d1Z9qq7ffuA3DRnyvyHoqw45KM+3R46MnjI9VLUGrf+pGXZRM5tZTuzsO8QOfi+S3K6hcZvJqmDODIS8DMAu01sLrlBtK6W4tYjUcQDzy6J9s9ADEGnQgg9kyqMLLHHdu08HDiOAPMLmj0+GY22Wdn0WIE3ufVS9PVXAsoeLF432ZVsDeUrMu1+iQo6oMfk8OjvYOGhzsms9wZ8e+tas/3VWZ8AdrpyT6JoAsE0gKctKbFw5beZEi4pSUpu5yK2PMhUfVPTmaRQuJVVgVLKqYxFY/iQjjcVj+LVBc651JurbtViBkduA5DVUpwJeBzNgq8M1No/kXdkho91ynuFMvveiayx2eRyJUhhrd0E87rovhySdvFULiy9UbNOfBeHuuT6KawKAElx4ZAemqTK9KSJnDYiHNDtHjT91ZMHj8bulgoWnNmNfytu+xU9g7LAni43KnBl4WKjGas9CMlXMOZcq0UrbBdGKRyuIQma442XlnPifZcfmbL09yAQqZgwFxOgJ+iou0WJ/Dif8A/SQEnPystYD5fqp7aevDGhp45nsOB7myzPHqpzh4j4n2PZqjnlvpbjx9qtUEk9ySmzyM0vUjM5/f+03a3I9SAsP7MKvX5KxbJzWlAP8AQfrl+irlWfEVI4LXCKeNzvLugO6A3z9MluXcLhdZR9BYVTbrB2TyWAOGYSOGzAtBHEA/MJ+FHHHpe5WVX67Bz+W3Yqu1OBzXBYCLG+7e7bq/yhN0tjow/Kz1q9mmz7ZREBKLEZDO+XBSrXJBhXouWyufO7tokemkr0pI9RtZUWWWiQlXVdlTsdxBxu1gueiUx/Ho4yGueAT98E0xOD4Efxj4mubcObnnyU+7VpqRAUmEzSOJcA1rfMSdFX8Ue0yBsWjSQD1untXtPNI34cbBGw+Z17kqOeQ0C3z9dV1YY2eXHy8kvUN3U5a4314Je26LcguRPud4+i8TO1VKlj9koT4j6e6sFFUNa0i+v7KApxmrRg1DdwFuRKTI8TFCxzw3wkNaBrxPIK0UEWgUdALmw0HurFhcCzCEyqawyCynYlHUjVJMXRCPSFxCA8RaXOpSMsmvIZpUngobaCu+HGbccrDiT5Qlt1GztTtqKj4hf/SDmeZGoHa4VOxCe7nE5chw009lZahrpHiP8rPMdBfV5Pr7KnV0t94/1ONvn+yhO7t0+IZ7hNzwYN4/fdItPhHe691DrNt/UkgfKOyYtRNbk53crlTr/wCR7BcqXXcf7vcr3WDUjhl8k6VXrYj8QTThsNTd0QsGSDMs6O5t66rX6HFI5WB8b2vacwWm4XzAw8OCltnsTlp5QWSOaCRvNByI6jRTzx9xXDP1X0g+YJB0wUTh/wDNYHB7hcA8Es+geNH/ADC5rlV9RICcLy6oCh3xSDims8ch/MUnzpvika/FGMFyVSdptpSxjnDLl19FMyYeAN52Z6rMduqzekEY0Cbjlzy0M7MMbUv+HGIQNNTVVbTJIbRxg56tLja/E2ASmzVJ4q+ofvfwkcZey7rtaXuO4wA8b5Km4RO2zo3P+Hcte15BIa5vO3CylcZxsGD+EgI+G5wkneG7omkHl8P9IXZZ3pxb637QMUpSs1zYBN2hOIHEnvl6JiF48gk5Tl3XqR3DmkZnZ2WH9FaQXcOpV+win3W3Orvbgqds7BvzN5DNX+Blkmflt8JHD481a6COwVewpuatFIMk+ESqQgUgxR8RT2JyqCiEIQDeZxAJKpmNSOfKGA2dwvkA52QOfJu8Va8TmAbmbAC57DMrPo6tr5JKiR1mnwN6Emw+QUs76Uwnshj0ogpiG33pLgG+ZaDYn1VKkGYHAAX7lSu0OICWUluTG2awf9W/vqoKSckEc8z+iSKeiFY7eebaaBIzOsR0BP0Xpnn7A+yaVsmfpZNGZGUWo7j9SnIG81391/v5pvTN9z7f5Tmm1seNx89E1ThgdU6hN7HiPZNp22PZKU7rG6yjHy2T8P8AEiYxG43tm3srwTksc2YqnxbrrG2oIzHbJabT4wxzLgi/EFclynbt1vs6mK4IknTzh3Xtn7JxJFIcmMN+uQU+hvSA2iqQxh52WJYu4ukLjxW0Y9s7UyAi8Y53cbnkNFSNoPw/lha1z5oy4kDdYHF1zwHNPxcmON7Jy2ZTUU+qoA1gvdr9bahwPFpTNotqtMo/w+kdFvSyZ7uTdd3pdZ9idC6OQtJvbLur8fLMutubLE3IubpzBHYfRJManThZg+Z7nT6BVtEhu03d2SEzswnFKPN9UzcfF6rZ5FvS27IRZudbsrewqs7LDwX7Kz04uQpW9tyTuEtVkpgoXDIlOU4VcIlTtpTiJ6bhdBVAffEQmm+uIYpW2G0IDfhtIJcDc8gVnM05cczlyvkneI1BcXPd5nFRMTrutwz/AMrnnfbp1ro5nd9Ao8a2S9RJkU2a7InkFoJsd4j8lHVz/Gn1NlcqMnzcSmxieVKRGzXXCVphn6fUJF3lPXJK0pzWscxGPPe9CkIdVI1jd5juYzTCJZfAnldtiK7deIybA6H2Wz4ZI2RubW7w1yHzWB4Mbbrhq0/Ra/sxiZIF9V5vLqZ7+1e9LZkBYAD0svEZ1K9SOuLhNIzckHgCVtpDHEqoMBe45N06uOluqj2YW6W8knntdo/+TdbD/seJ9Es+0tTY5sgbvu5GR3kHWwBPqp6lZ4c+OZU/jun3pE4m8MiNzZoFx8liGOt35XPtbW3QLYdpJP5fw+RIKzjGMKIhkm4NcG/NU4cpjm3XSnvhsPReqnKO/TPvoPopJ9Pvxi2tvZIyBroSCPEf9Lt+ZLENSus09f3TYeYDmVIS0DmsJGYBGf7c0hRwH4gLshrmqTKXdTvqLls8N0Fvb2VswuO5VSoZGgtLTfgVYKaoLbKXs9XqijFlJRNVMpMYI1U3RYwCrY5xLVT66kIaoOSwKpAF1CEMfPuIVOe6NbfVJQtyuP7flqk6mB4cd4Zm31+wl2kN67oz7kZqE8Oi+TeqeSSPvJJu8tuZP0Xn4l3aZa90q4cOQ91rJSIFmqKec/kpac+EfNQzimhMisjvCB6r3GcwkpT9+i9g5D1/RDEjA/geORUe5m64t65ffZOYneJeqiPfZvjUZO/RY1M4A27Ve9nZja/I2PQhUjZWQObbiCFcMLk3ZbDyvy/9DT6Zei8r8i92OjGdNCw2q3hYpzFHmVC0TXcPuyno94hZx5bnaVmkDgm7apLsnGUg+gsFMU9SBHmcxwVbqWmKdzb5SOD28idHD2VnhkGlh6oxv8q3Ke0E6kDpS+QZEXaOoy9rKuYvSmSmnaOBdlzsVea1mV7ae3FQb4GtklYTYSeNuXTxBF3jW43bH8Na0xy7xs5trDnzUTM85W04/NW3HcEaHBwdui7myEdDqQOGYURU0hiBNg5hyDgurDkl7+25QhoBc8NPvRJ08fxH2ALydAOPfoigw97yA4EMJ9ewWu7M7NxwMBDBc8TmfmjPkmHjyXXuqjg+wriA6VxZ0br/AIUnV7PuYP5Mhdb8rs79nc1eZYBbmeSYyQkarnvJnvex0osU5B3XtLXcin0Mp1CmMQpY5PMM+ds1DOonx5/k5q+HLKWxL0eIlqm6XFLqpRlO4H2K6Mc7CXFb/wCOCFXfjIT/APSl0zXFbmodfO2vbl9Eyqcm9/8A9ZJVzrlzjq429NU0rJL6cTl2CzGaki9N4Dck8z8gvTZLk/fZJb+60nj+680w9j9U5dvdWfD96WUW9P646BMoxc34DNbCZPczbWHReuHY+6TkNyvcRyQC17G/RK00+6/PyuyPuCkScvQLyf0CxqcwB3w6kNOTX6K6/Bs64HI+oP7XWfxO3mA/mbbPt9hX/AKr40bXfm0d0IyXB+Xjq/JfjvS2UE5Fj0+yrPRPBaFU6Ft2ffDJTWGT2yuuXjy1WZwpj2GfFj8Pnb4mdxw9UngtaJW7rspG5OadQR7hTAUJjuDfEIlieYpm+V4zv0cOIVrj3uFl61UuGAmxFsvQqPqaQX3XD+0+1lG020MkfhrYvh8BK3OJ3c/kPdTMzw9gIII1a4Zj6IurGeFXddtQY5WX3xdpaBZ2VjccDkE4psJjBt/Dxg8HFoy9OacYxCXtD2XEsZuD7jsVI0NQ6RgeAL2168iFKTtS3pGNwKNviIu48bD5AcAnNO8sO6fFytnbunhc43DvD2F/9LzTua02boeh9U2u+i76KMHH3SNXG3j8rp4wOva4A7INMO55ptdFV6WDPP5BJ1EIcwtOimamHqoushs3PVS8VqsRtsS06hOo15xFtnA89V2Irvxy3NkpdC4hOVms2n/oppP+XsUIVopTOq09f0SlLp99UIWlJYh5k1h8p++KEJoXLy4V6i+/khCweyjtD2CB+i4hY0/w3Q9lcdhtH9x7IQuX8r9FeJfsJ8ju5Tql1HdCF5uPo+Swt0XH6H0QhdiPpFYh/wAM39p9ioD8Pf8Ahf3KEKePg+Sel8zuwXnZnR/9xQhZj+zb+qXfqmZ83qUIW5Eh2V16EKnoGUqiMX1Qhc+YivYtoO68Q6IQuri/UtLoQhWK/9k="
            ></v-img>
          </v-list-item-avatar>

          <v-list-item-content
            @click="gotoProfile"
          >
            <v-list-item-title>{{username}}</v-list-item-title>
          </v-list-item-content>

          <v-row
            align="center"
            justify="end"
          >
            <v-icon 
              @click="like"
              class="mr-1" 
              small
              :color="status"
            >
              mdi-heart
            </v-icon>
            <span class="mr-2">{{like_cnt}}</span>
            
            <v-icon class="mr-1" small>
              mdi-comment-text-outline
            </v-icon>
            <span class="subheading">{{comment_cnt}}</span>
          </v-row>
        </v-list-item>
      </v-card-actions>
    </v-card>
</template>

<script>
export default {
  name: 'UsernameBar',
  data() {
    return {
      status: 'red',
      like_cnt: 256,
      comment_cnt: 45,
      username: 'sejinkwon',
    }
  },
  methods: {
    like: function () {
      if (this.status == 'red') {
        this.status = 'black'
        this.like_cnt -= 1
      } else {
        this.status = 'red'
        this.like_cnt += 1
      }
    },
    gotoProfile: function () {
      this.$router.push({ name: 'Profile', params: {username: this.username }})
    }
  }
}
</script>

<style>

</style>